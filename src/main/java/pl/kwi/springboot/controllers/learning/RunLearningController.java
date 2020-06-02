package pl.kwi.springboot.controllers.learning;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.kwi.springboot.commands.learning.RunLearningCommand;
import pl.kwi.springboot.db.entities.CardEntity;
import pl.kwi.springboot.db.entities.WordEntity;
import pl.kwi.springboot.enums.LearningModeEnum;

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
		
		if (finishRun(command) && isManualRepeat(session)) {
			return "redirect:/resultLearning/display";
		} else if (finishRun(command) && isAuthomaticRepeat(session)) {
			return "redirect:/resultLearning/repeat";
		} else if (finishRun(command)) {			
			return "redirect:/resultLearning/display";
		}
		
		handleRun(command, session);		
		return "learning/runLearning";
		
	}
	
	@RequestMapping(value="/notValid")
	public String notValid(
			@ModelAttribute("command") RunLearningCommand command,
			HttpSession session) {
		
		handleNotValidCards(command, session);
		
		if (finishRun(command) && isManualRepeat(session)) {
			return "redirect:/resultLearning/display";
		} else if (finishRun(command) && isAuthomaticRepeat(session)) {
			return "redirect:/resultLearning/repeat";
		} else if (finishRun(command)) {			
			return "redirect:/resultLearning/display";
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
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute("cards");
		redirectAttributes.addAttribute("skippedCardsCount", cards.size() - command.getCardNumber() + 1);
		
	}
	
	@SuppressWarnings("unchecked")
	private void handleNotValidCards(RunLearningCommand command, HttpSession session) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute("cards");
		List<CardEntity> notValidCards = (List<CardEntity>)session.getAttribute("notValidCards");
		CardEntity card = cards.get(command.getCardNumber() - 2);
		notValidCards.add(card);
		session.setAttribute("notValidcards", notValidCards);
		
	}
	
	private void handleRun(RunLearningCommand command, HttpSession session) {
		
		CardEntity card = getCard(command, session);
		command.setWord(getWord(command, card));
		handleNavigationButtons(command, card.getWords().size());
		
	}
	
	private boolean finishRun(RunLearningCommand command) {
		
		if (command.getCardNumber() > command.getCardCount()) {
			return true;
		}
		return false;
		
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
	
	private boolean isManualRepeat(HttpSession session) {
		boolean result = false;
		
		LearningModeEnum learningMode = (LearningModeEnum)session.getAttribute("selectedLearningMode");
		if (LearningModeEnum.MANUAL.equals(learningMode) && (Boolean)session.getAttribute("manualLearningModeRepeat")) {
			result = true;
		}
		
		return result;
	}
	
	private boolean isAuthomaticRepeat(HttpSession session) {
		boolean result = false;
		
		LearningModeEnum learningMode = (LearningModeEnum)session.getAttribute("selectedLearningMode");
		if (LearningModeEnum.AUTHOMATIC.equals(learningMode) && (Boolean)session.getAttribute("authomaticLearningModeRepeat")) {
			result = true;
		}
		
		return result;
	}
	
}