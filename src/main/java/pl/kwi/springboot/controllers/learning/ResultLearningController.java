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
import pl.kwi.springboot.enums.LearningModeEnum;

@Controller
@RequestMapping(value="/resultLearning")
public class ResultLearningController {

	@RequestMapping(value="/display")
	public String display(
			@ModelAttribute("command") ResultLearningCommand command,
			HttpSession session) {
		
		handleResult(command, session);
		handleLearningMode(command, session);
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
		LearningModeEnum learningMode = (LearningModeEnum)session.getAttribute("selectedLearningMode");
		if (LearningModeEnum.MANUAL.equals(learningMode)) {
			attributes.addAttribute("selectedLearningMode", LearningModeEnum.MANUAL);
			attributes.addAttribute("manualLearningModeRepeat", session.getAttribute("manualLearningModeRepeat"));
		} else {
			attributes.addAttribute("selectedLearningMode", LearningModeEnum.AUTHOMATIC);
			attributes.addAttribute("authomaticLearningModeRepeat", session.getAttribute("authomaticlLearningModeRepeat"));
		}
		
		return "redirect:/runLearning/run";
		
	}
	
	@SuppressWarnings("unchecked")
	private void handleResult(ResultLearningCommand command, HttpSession session) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute("cards");
		List<CardEntity> notValidCards = (List<CardEntity>)session.getAttribute("notValidCards");
		LearningModeEnum learningMode = (LearningModeEnum)session.getAttribute("selectedLearningMode");
		boolean manualLearningModeRepeat = (Boolean)session.getAttribute("manualLearningModeRepeat");
		if (LearningModeEnum.MANUAL.equals(learningMode) && manualLearningModeRepeat) {
			command.setCardsCount((Integer)session.getAttribute("cardsCount"));
			command.setValidCardsCount((Integer)session.getAttribute("validCardsCount"));
			command.setNotValidCardsCount((Integer)session.getAttribute("notValidCardsCount"));
		} else {
			command.setCardsCount(cards.size());
			command.setValidCardsCount(cards.size() - notValidCards.size() - command.getSkippedCardsCount());
			command.setNotValidCardsCount(notValidCards.size());
		}		
		
	}
	
	private void handleLearningMode(ResultLearningCommand command, HttpSession session) {
		
		LearningModeEnum learningMode = (LearningModeEnum)session.getAttribute("selectedLearningMode");
		if (LearningModeEnum.MANUAL.equals(learningMode)) {
			command.setManualLeartingMode(true);
			command.setRepeat((Boolean)session.getAttribute("manualLearningModeRepeat"));
		} else {
			command.setManualLeartingMode(false);
			command.setRepeat((Boolean)session.getAttribute("authomaticLearningModeRepeat"));
		}
		
	}
	
}