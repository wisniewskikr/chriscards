package pl.kwi.springboot.controllers.learning;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.learning.ResultLearningCommand;
import pl.kwi.springboot.db.entities.CardEntity;

@Controller
@RequestMapping(value="/resultLearning")
public class ResultLearningController {

	@RequestMapping(value="/display")
	public String display(
			@ModelAttribute("command") ResultLearningCommand command,
			HttpSession session) {
		
		handleResult(command, session);
		return "learning/resultLearning";
		
	}
	
	@SuppressWarnings("unchecked")
	private void handleResult(ResultLearningCommand command, HttpSession session) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute("cards");
		List<CardEntity> notValidCards = (List<CardEntity>)session.getAttribute("notValidCards");
		command.setCardsCount(cards.size());
		command.setValidCardsCount(cards.size() - notValidCards.size());
		command.setNotValidCardsCount(notValidCards.size());
		command.setSkippedCardsCount(0);
		
	}
	
}