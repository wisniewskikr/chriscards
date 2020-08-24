package pl.kwi.springboot.controllers.more;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kwi.springboot.commands.more.MoreEditRunCommand;
import pl.kwi.springboot.db.entities.CardEntity;
import pl.kwi.springboot.db.entities.DeckEntity;
import pl.kwi.springboot.db.entities.WordEntity;
import pl.kwi.springboot.enums.LanguageEnum;
import pl.kwi.springboot.pagination.checkboxPagination.controllers.AbstrCheckboxPaginationController;
import pl.kwi.springboot.services.intf.DeckService;

@Controller
@RequestMapping(value="/more/edit/run")
public class MoreEditRunController extends AbstrCheckboxPaginationController {
	
	
	private static final String CARDS_ATTRIBUTE = "cards";
	private static final int DEFAULT_CARD_NUMBER = 1;	
	
	@Autowired
	private DeckService deckService;


	@RequestMapping
	public String displayPage(
			@ModelAttribute("command") MoreEditRunCommand command,
			HttpSession session) {
		
		DeckEntity deck = deckService.findById(command.getSelectedItem());
		
		command.setDeckName(deck.getName());
		command.setCurrentCardNumber(DEFAULT_CARD_NUMBER);
		command.setAllCardsCount(deck.getCards().size());
		command.setDisablePrevious(true);
		session.setAttribute(CARDS_ATTRIBUTE, deck.getCards());
		
		readNewCardCommand(command, session, 0);			
		handlePreviousAndNext(command);
		
		return "more/moreEditRun";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/next", method = RequestMethod.POST)
	public String next(
			@Validated @ModelAttribute("command") MoreEditRunCommand command,
			BindingResult bindingResult,
			HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			return "more/moreEditRun";
		}
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		adjustCardsInSession(command, session, cards);		
		if(command.getCurrentCardNumber() == command.getAllCardsCount()) {
			handleNewCard(command, session);			
		} else {
			handleExistingCard(command, session, command.getCurrentCardNumber() + 1);
		}			
		handlePreviousAndNext(command);
		
		return "more/moreEditRun";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/previous", method = RequestMethod.POST)
	public String previuos(
			@Validated @ModelAttribute("command") MoreEditRunCommand command,
			BindingResult bindingResult,
			HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			return "more/moreEditRun";
		}
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		adjustCardsInSession(command, session, cards);
		handleExistingCard(command, session, command.getCurrentCardNumber() - 1);		
		handlePreviousAndNext(command);
				
		return "more/moreEditRun";
		
	}
	
	private void handleNewCard(MoreEditRunCommand command, HttpSession session) {
		
		updateCardInSessionAttribute(session, command, command.getCurrentCardNumber() - 1);
		cleanNewCardCommand(command);
		command.setCurrentCardNumber(command.getCurrentCardNumber() + 1);
		command.setAllCardsCount(command.getAllCardsCount() + 1);
		
	}
	
	private void cleanNewCardCommand(MoreEditRunCommand command) {
		
		command.setPolishWord(null);
		command.setPolishSentence(null);
		
		command.setEnglishWord(null);
		command.setEnglishSentence(null);
		
		command.setRussianWord(null);
		command.setRussianSentence(null);
		
		command.setSpainWord(null);
		command.setSpainSentence(null);
		
	}
	
	private void adjustCardsInSession(MoreEditRunCommand command, HttpSession session, List<CardEntity> cards) {
		
		if(command.getAllCardsCount() != (cards.size())) {
			addNewCardToSessionAttribute(session, command);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void addNewCardToSessionAttribute(HttpSession session, MoreEditRunCommand command) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		cards.add(createCard(command));	
		session.setAttribute(CARDS_ATTRIBUTE, cards);
		
	}
	
	private CardEntity createCard(MoreEditRunCommand command) {
		
		List<WordEntity> words = new ArrayList<WordEntity>();
		WordEntity word;
		word = new WordEntity(command.getPolishWord(), command.getPolishSentence(), LanguageEnum.POLISH);
		words.add(word);
		word = new WordEntity(command.getEnglishWord(), command.getEnglishSentence(), LanguageEnum.ENGLISH);
		words.add(word);
		word = new WordEntity(command.getRussianWord(), command.getRussianSentence(), LanguageEnum.RUSSIAN);
		words.add(word);
		word = new WordEntity(command.getSpainWord(), command.getSpainSentence(), LanguageEnum.SPAIN);
		words.add(word);
		return new CardEntity(words);
		
	}
	
	private void handleExistingCard(MoreEditRunCommand command, HttpSession session, int nextCardNumber) {
		
		updateCardInSessionAttribute(session, command, command.getCurrentCardNumber() - 1);
		readNewCardCommand(command, session, nextCardNumber - 1);
		command.setCurrentCardNumber(nextCardNumber);
		
	}
	
	@SuppressWarnings("unchecked")
	private void updateCardInSessionAttribute(HttpSession session, MoreEditRunCommand command, int index) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		cards.set(index, createCard(command));	
		session.setAttribute(CARDS_ATTRIBUTE, cards);
		
	}
	
	@SuppressWarnings("unchecked")
	private void readNewCardCommand(MoreEditRunCommand command, HttpSession session, int index) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		CardEntity card = cards.get(index);
		
		WordEntity polishWord = card.getWords().get(0);		
		command.setPolishWord(polishWord.getWord());
		command.setPolishSentence(polishWord.getSentence());
		
		WordEntity englishhWord = card.getWords().get(1);
		command.setEnglishWord(englishhWord.getWord());
		command.setEnglishSentence(englishhWord.getSentence());
		
		WordEntity russianhWord = card.getWords().get(2);
		command.setRussianWord(russianhWord.getWord());
		command.setRussianSentence(russianhWord.getSentence());
		
		WordEntity spainhWord = card.getWords().get(3);
		command.setSpainWord(spainhWord.getWord());
		command.setSpainSentence(spainhWord.getSentence());
		
	}
	
	private void handlePreviousAndNext(MoreEditRunCommand command) {
		
		if(DEFAULT_CARD_NUMBER == command.getCurrentCardNumber()) {
			command.setDisablePrevious(true);
		} else {
			command.setDisablePrevious(false);
		}
		
	}

	
}