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

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/run")
	public String displayPage(
			@ModelAttribute("command") RunLearningCommand command,
			HttpSession session) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute("cards");
		CardEntity card = cards.get(command.getCardNumber() - 1);
		WordEntity word = card.getWords().get(command.getCardNumber() - 1);
		command.setWord(word);
		
		return "learning/runLearning";
	}
	
}