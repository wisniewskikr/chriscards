package pl.kwi.springboot.controllers.learning;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.kwi.springboot.commands.learning.RunLearningCommand;
import pl.kwi.springboot.db.entities.CardEntity;
import pl.kwi.springboot.db.entities.WordEntity;
import pl.kwi.springboot.enums.LearningModeEnum;
import pl.kwi.springboot.enums.RedirectAttributesEnum;
import pl.kwi.springboot.enums.SessionAttributesEnum;

@Controller
@RequestMapping(value="/runLearning")
public class RunLearningController {

	@RequestMapping(value="/run")
	public String run(
			@ModelAttribute("command") RunLearningCommand command,
			HttpSession session) {
		
		handleRun(command, session);		
		return "learning/runLearning";
		
	}
	
	@RequestMapping(value="/valid")
	public String valid(
			@ModelAttribute("command") RunLearningCommand command,
			HttpSession session) {
		
		String finishUrl = handleFinishRun(command, session);
		if (StringUtils.isNoneBlank(finishUrl)) {
			return finishUrl;
		}		
		handleRun(command, session);		
		return "learning/runLearning";
		
	}
	
	@RequestMapping(value="/notValid")
	public String notValid(
			@ModelAttribute("command") RunLearningCommand command,
			HttpSession session) {
		
		handleNotValidCards(command, session);
		String finishUrl = handleFinishRun(command, session);
		if (StringUtils.isNoneBlank(finishUrl)) {
			return finishUrl;
		}		
		handleRun(command, session);		
		return "learning/runLearning";
		
	}	
	
	@RequestMapping(value="/skip")
	public String skip(
			@ModelAttribute("command") RunLearningCommand command,
			HttpSession session,
			RedirectAttributes redirectAttributes) {		
			
			handleSkippedCards(command, session, redirectAttributes);
			return "redirect:/resultLearning/display";
		
	}
	
	@SuppressWarnings("unchecked")
	private void handleSkippedCards(RunLearningCommand command,
			HttpSession session,
			RedirectAttributes redirectAttributes) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(SessionAttributesEnum.CARDS.name());
		redirectAttributes.addAttribute(RedirectAttributesEnum.SKIPPED_CARDS_COUNT.getValue(), cards.size() - command.getCardNumber() + 1);
		
	}
	
	@SuppressWarnings("unchecked")
	private void handleNotValidCards(RunLearningCommand command, HttpSession session) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(SessionAttributesEnum.CARDS.name());
		List<CardEntity> notValidCards = (List<CardEntity>)session.getAttribute(SessionAttributesEnum.NOT_VALID_CARDS.name());
		CardEntity card = cards.get(command.getCardNumber() - 2);
		notValidCards.add(card);
		session.setAttribute(SessionAttributesEnum.NOT_VALID_CARDS.name(), notValidCards);
		
	}
	
	private void handleRun(RunLearningCommand command, HttpSession session) {
		
		CardEntity card = getCard(command, session);
		command.setWord(getWord(command, card));
		handleNavigationButtons(command, card.getWords().size());
		handleLearningMode(command, session);
		
	}
	
	private void handleLearningMode(RunLearningCommand command, HttpSession session) {
		
		LearningModeEnum learningMode = (LearningModeEnum)session.getAttribute(SessionAttributesEnum.SELECTED_LEARNING_MODE.name());
		if (LearningModeEnum.MANUAL.equals(learningMode)) {
			command.setManualLeartingMode(true);
			command.setRepeat((Boolean)session.getAttribute(SessionAttributesEnum.MANUAL_REPEAT.name()));
		} else {
			command.setManualLeartingMode(false);
			command.setRepeat((Boolean)session.getAttribute(SessionAttributesEnum.AUTHOMATIC_REPEAT.name()));
		}
		
	}
	
	private boolean finishRun(RunLearningCommand command) {
		
		if (command.getCardNumber() > command.getCardCount()) {
			return true;
		}
		return false;
		
	}
	
	@SuppressWarnings("unchecked")
	private CardEntity getCard(RunLearningCommand command, HttpSession session) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(SessionAttributesEnum.CARDS.name());
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
	
	private String handleFinishRun(RunLearningCommand command,
			HttpSession session) {
		
		if (finishRun(command) && isManualRepeat(session) && nothingToManualRepeat(session)) {
			return "redirect:/resultLearning/display";
		} else if (finishRun(command) && isManualRepeat(session) && !nothingToManualRepeat(session)) {
			handleManualRepeat(session);
			return "redirect:/resultLearning/repeat";
		} else if (finishRun(command) && isAuthomaticRepeat(session)) {
			return "redirect:/resultLearning/repeat";
		} else if (finishRun(command)) {			
			return "redirect:/resultLearning/display";
		}
		
		return null;
		
	}
	
	private boolean isManualRepeat(HttpSession session) {
		boolean result = false;
		
		LearningModeEnum learningMode = (LearningModeEnum)session.getAttribute(SessionAttributesEnum.SELECTED_LEARNING_MODE.name());
		if (LearningModeEnum.MANUAL.equals(learningMode) && (Boolean)session.getAttribute(SessionAttributesEnum.MANUAL_REPEAT.name())) {
			result = true;
		}
		
		return result;
	}
	
	private boolean isAuthomaticRepeat(HttpSession session) {
		boolean result = false;
		
		LearningModeEnum learningMode = (LearningModeEnum)session.getAttribute(SessionAttributesEnum.SELECTED_LEARNING_MODE.name());
		if (LearningModeEnum.AUTHOMATIC.equals(learningMode) && (Boolean)session.getAttribute(SessionAttributesEnum.AUTHOMATIC_REPEAT.name())) {
			result = true;
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private boolean nothingToManualRepeat(HttpSession session) {
		
		List<CardEntity> notValidCards = (List<CardEntity>)session.getAttribute(SessionAttributesEnum.NOT_VALID_CARDS.name());
		if (notValidCards.isEmpty()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void handleManualRepeat(HttpSession session) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(SessionAttributesEnum.CARDS.name());
		List<CardEntity> notValidCards = (List<CardEntity>)session.getAttribute(SessionAttributesEnum.NOT_VALID_CARDS.name());
		session.setAttribute(SessionAttributesEnum.CARDS_COUNT.name(), Integer.valueOf(cards.size()));
		session.setAttribute(SessionAttributesEnum.VALID_CARDS_COUNT.name(), Integer.valueOf(cards.size() - notValidCards.size()));
		session.setAttribute(SessionAttributesEnum.NOT_VALID_CARDS_COUNT.name(), Integer.valueOf(notValidCards.size()));
		session.setAttribute(SessionAttributesEnum.CARDS.name(), notValidCards);		
		
	}
	
}