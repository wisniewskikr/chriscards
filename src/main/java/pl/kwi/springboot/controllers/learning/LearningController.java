package pl.kwi.springboot.controllers.learning;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
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
import pl.kwi.springboot.enums.LearningModeEnum;
import pl.kwi.springboot.enums.RedirectAttributesEnum;
import pl.kwi.springboot.enums.SessionAttributesEnum;
import pl.kwi.springboot.services.intf.DeckService;

@Controller
@RequestMapping(value="/learning")
public class LearningController {
	
	
	@Autowired
	private DeckService deckService;
	
	@Value("${cards.count.learning}")
    private int cartsCount;
	

	@RequestMapping
	public String displayPage(
			@ModelAttribute("command") LearningCommand command) {
		
		Page<DeckEntity> deckPage = deckService.find(PageRequest.of(command.getCurrentPage() - 1, cartsCount, Sort.by(Sort.Direction.DESC, "modificationTimestamp")));
		command.setDecks(deckPage.getContent());		
		command.setSelectedLearningMode(LearningModeEnum.MANUAL);	
		handlePagination(command, deckPage);
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
		// TODO: fix 1 as count
		List<DeckEntity> decks = deckService.find(PageRequest.of(0,1,Sort.by(Sort.Direction.DESC, "id"))).getContent();
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
	
	private void handlePagination(LearningCommand command, Page<DeckEntity> page) {
		
		List<Integer> pages = new ArrayList<Integer>();
		int first = getFirst(command.getCurrentPage(), page.getTotalPages());
		int last = getLast(command.getCurrentPage(), page.getTotalPages());
		for (int i = first; i <= last; i++) {
			pages.add(i);
		}
		command.setPages(pages);
		
		if (command.getCurrentPage() == 1) {
			command.setDisablePrevious(true);
		} else {
			command.setDisablePrevious(false);
		}
		
		if (command.getCurrentPage() == page.getTotalPages()) {
			command.setDisableNext(true);
		} else {
			command.setDisableNext(false);
		}
		
	}
	
	private int getFirst(int currentPage, int totalPages) {
		
		int result = 1;
		
		if (totalPages <= 5) {
			return result;
		}
		
		if ((currentPage - 1 ) > 0) {
			result = currentPage - 1;
		}
		
		if ((currentPage - 2) > 0) {
			result = currentPage - 2;
		}
		
		if ((currentPage - 3) > 0 && (currentPage + 2) > totalPages) {
			result = currentPage - 3;
		}
		
		if ((currentPage - 4) > 0 && (currentPage + 1) > totalPages) {
			result = currentPage - 4;
		}
		
		return result;
		
	}
	
	private int getLast(int currentPage, int totalPages) {
		
		int result = totalPages;
		
		if (totalPages <= 5) {
			return result;
		}
		
		if ((currentPage + 1) <= totalPages) {
			result = currentPage + 1;
		}
		
		if ((currentPage + 2) <= totalPages) {
			result = currentPage + 2;
		}
		
		if ((currentPage + 3 ) < totalPages  && (currentPage - 2) <= 0) {
			result = currentPage + 3;
		}
		
		if ((currentPage + 4) < totalPages  && (currentPage - 1) <= 0) {
			result = currentPage + 4;
		}		
		
		return result;
		
	}
	
}