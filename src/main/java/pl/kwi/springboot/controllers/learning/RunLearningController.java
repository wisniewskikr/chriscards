package pl.kwi.springboot.controllers.learning;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.learning.RunLearningCommand;
import pl.kwi.springboot.db.entities.CardEntity;
import pl.kwi.springboot.db.entities.WordEntity;

@Controller
@RequestMapping(value="/runLearning")
public class RunLearningController {

	@RequestMapping(value="/run")
	public String run(
			@ModelAttribute("command") RunLearningCommand command,
			HttpSession session) {
		
		CardEntity card = getCard(command, session);
		command.setWord(getWord(command, card));
		handleNavigationButtons(command, card.getWords().size());
		
		return "learning/runLearning";
	}
	
	@SuppressWarnings("unchecked")
	private CardEntity getCard(RunLearningCommand command, HttpSession session) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute("cards");
		return cards.get(command.getCardNumber() - 1);
		
	}
	
	private WordEntity getWord(RunLearningCommand command, CardEntity card) {
		return card.getWords().get(command.getWordNumber() - 1);
	}
	
	private void handleNavigationButtons(RunLearningCommand command, int wordsCount) {
		
		if (1 == command.getWordNumber()) {
			command.setFirstWord(true);
		} else {
			command.setFirstWord(false);
		}
		
		if (wordsCount == command.getWordNumber()) {
			command.setLastWord(true);
		} else {
			command.setLastWord(false);
		}
		
	}
	
}