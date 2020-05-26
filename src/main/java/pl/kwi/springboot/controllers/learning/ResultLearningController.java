package pl.kwi.springboot.controllers.learning;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	@RequestMapping(value="/repeat")
	public String repeat(
			@ModelAttribute("command") ResultLearningCommand command,
			RedirectAttributes attributes,
			HttpSession session) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute("cards");
		
		session.setAttribute("cards", cards);
		session.setAttribute("notValidCards", new ArrayList<CardEntity>());
		
		attributes.addAttribute("cardNumber", 1);
		attributes.addAttribute("cardCount", cards.size());
		attributes.addAttribute("wordNumber", 1);
		attributes.addAttribute("wordCount", cards.get(0).getWords().size());
		
		return "redirect:/runLearning/run";
		
	}
	
	@SuppressWarnings("unchecked")
	private void handleResult(ResultLearningCommand command, HttpSession session) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute("cards");
		List<CardEntity> notValidCards = (List<CardEntity>)session.getAttribute("notValidCards");
		command.setCardsCount(cards.size());
		command.setValidCardsCount(cards.size() - notValidCards.size() - command.getSkippedCardsCount());
		command.setNotValidCardsCount(notValidCards.size());
		
	}
	
}