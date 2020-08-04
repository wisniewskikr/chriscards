package pl.kwi.springboot.controllers.learning;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.kwi.springboot.commands.learning.LearningCommand;
import pl.kwi.springboot.db.entities.CardEntity;
import pl.kwi.springboot.db.entities.DeckEntity;
import pl.kwi.springboot.db.repositories.DeckRepository;
import pl.kwi.springboot.enums.LearningModeEnum;
import pl.kwi.springboot.enums.RedirectAttributesEnum;
import pl.kwi.springboot.enums.SessionAttributesEnum;

@Controller
@RequestMapping(value="/learning")
public class LearningController {
	
	@Autowired
	private DeckRepository deckRepository;

	@RequestMapping
	public String displayPage(
			@ModelAttribute("command") LearningCommand command) {
		
		command.setSelectedLearningMode(LearningModeEnum.MANUAL);		
		return "learning/learning";
		
	}
	
	@RequestMapping(value="/run", method = RequestMethod.POST)
	public String run(
			@ModelAttribute("command") LearningCommand command,
			RedirectAttributes attributes,
			HttpSession session) {
		
		List<CardEntity> cards = getCards(command);
		handleSession(session, command, cards);
		handleRedirectAttributes(attributes, cards);
		
		return "redirect:/runLearning/run";
		
	}
	
	private List<CardEntity> getCards(LearningCommand command) {
		
		List<CardEntity> cards = new ArrayList<CardEntity>();
		List<DeckEntity> decks = deckRepository.findLastDecks(PageRequest.of(0,command.getDeckCount(),Sort.by(Sort.Direction.DESC, "id"))).getContent();
		for (DeckEntity deck : decks) {
			cards.addAll(deck.getCards());
		}
		return cards;
				
	}
	
	private void handleSession(HttpSession session, LearningCommand command, List<CardEntity> cards) {
		
		session.setAttribute(SessionAttributesEnum.CARDS.name(), cards);
		session.setAttribute(SessionAttributesEnum.NOT_VALID_CARDS.name(), new ArrayList<CardEntity>());
		session.setAttribute(SessionAttributesEnum.SELECTED_LEARNING_MODE.name(), command.getSelectedLearningMode());
		if (LearningModeEnum.MANUAL.equals(command.getSelectedLearningMode())) {
			session.setAttribute(SessionAttributesEnum.MANUAL_REPEAT.name(), Boolean.valueOf(command.isManualLearningModeRepeat()));
		} else {
			session.setAttribute(SessionAttributesEnum.AUTHOMATIC_REPEAT.name(), Boolean.valueOf(command.isAuthomaticLearningModeRepeat()));
			session.setAttribute(SessionAttributesEnum.AUTHOMATIC_PLAY_SPEECH.name(), Boolean.valueOf(command.isPlaySpeech()));
		}
		
	}
	
	private void handleRedirectAttributes(RedirectAttributes attributes, List<CardEntity> cards) {
		
		attributes.addAttribute(RedirectAttributesEnum.CARD_NUMBER.getValue(), 1);
		attributes.addAttribute(RedirectAttributesEnum.CARD_COUNT.getValue(), cards.size());
		attributes.addAttribute(RedirectAttributesEnum.WORD_NUMBER.getValue(), 1);
		attributes.addAttribute(RedirectAttributesEnum.WORD_COUNT.getValue(), cards.get(0).getWords().size());
		
	}
	
}