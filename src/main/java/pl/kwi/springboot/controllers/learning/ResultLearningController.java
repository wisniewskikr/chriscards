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
import pl.kwi.springboot.enums.RedirectAttributesEnum;
import pl.kwi.springboot.enums.SessionAttributesEnum;

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
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(SessionAttributesEnum.CARDS.name());
		
		session.setAttribute(SessionAttributesEnum.CARDS.name(), cards);
		session.setAttribute(SessionAttributesEnum.NOT_VALID_CARDS.name(), new ArrayList<CardEntity>());
		
		attributes.addAttribute(RedirectAttributesEnum.CARD_NUMBER.getValue(), 1);
		attributes.addAttribute(RedirectAttributesEnum.CARD_COUNT.getValue(), cards.size());
		attributes.addAttribute(RedirectAttributesEnum.WORD_NUMBER.getValue(), 1);
		attributes.addAttribute(RedirectAttributesEnum.WORD_COUNT.getValue(), cards.get(0).getWords().size());
		LearningModeEnum learningMode = (LearningModeEnum)session.getAttribute(SessionAttributesEnum.SELECTED_LEARNING_MODE.name());
		if (LearningModeEnum.MANUAL.equals(learningMode)) {
			attributes.addAttribute(RedirectAttributesEnum.SELECTED_LEARNING_MODE.getValue(), LearningModeEnum.MANUAL);
			attributes.addAttribute(RedirectAttributesEnum.MANUAL_REPEAT.getValue(), session.getAttribute(SessionAttributesEnum.MANUAL_REPEAT.name()));
		} else {
			attributes.addAttribute(RedirectAttributesEnum.SELECTED_LEARNING_MODE.getValue(), LearningModeEnum.AUTHOMATIC);
			attributes.addAttribute(RedirectAttributesEnum.AUTHOMATIC_REPEAT.getValue(), session.getAttribute(SessionAttributesEnum.AUTHOMATIC_REPEAT.name()));
		}
		
		return "redirect:/runLearning/run";
		
	}
	
	@SuppressWarnings("unchecked")
	private void handleResult(ResultLearningCommand command, HttpSession session) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(SessionAttributesEnum.CARDS.name());
		List<CardEntity> notValidCards = (List<CardEntity>)session.getAttribute(SessionAttributesEnum.NOT_VALID_CARDS.name());
		LearningModeEnum learningMode = (LearningModeEnum)session.getAttribute(SessionAttributesEnum.SELECTED_LEARNING_MODE.name());
		boolean manualLearningModeRepeat = (Boolean)session.getAttribute(SessionAttributesEnum.MANUAL_REPEAT.name());
		if (LearningModeEnum.MANUAL.equals(learningMode) && manualLearningModeRepeat) {
			command.setCardsCount((Integer)session.getAttribute(SessionAttributesEnum.CARDS_COUNT.name()));
			command.setValidCardsCount((Integer)session.getAttribute(SessionAttributesEnum.VALID_CARDS_COUNT.name()));
			command.setNotValidCardsCount((Integer)session.getAttribute(SessionAttributesEnum.NOT_VALID_CARDS_COUNT.name()));
		} else {
			command.setCardsCount(cards.size());
			command.setValidCardsCount(cards.size() - notValidCards.size() - command.getSkippedCardsCount());
			command.setNotValidCardsCount(notValidCards.size());
		}		
		
	}
	
	private void handleLearningMode(ResultLearningCommand command, HttpSession session) {
		
		LearningModeEnum learningMode = (LearningModeEnum)session.getAttribute(SessionAttributesEnum.SELECTED_LEARNING_MODE.name());
		if (LearningModeEnum.MANUAL.equals(learningMode)) {
			command.setManualLeartingMode(true);
			command.setRepeat((Boolean)session.getAttribute(SessionAttributesEnum.MANUAL_REPEAT.name()));
		} else {
			command.setManualLeartingMode(false);
			command.setRepeat((Boolean)session.getAttribute(SessionAttributesEnum.AUTHOMATIC_REPEAT.name()));
		}
		
	}
	
}