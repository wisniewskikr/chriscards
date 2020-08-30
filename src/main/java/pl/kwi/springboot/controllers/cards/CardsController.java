package pl.kwi.springboot.controllers.cards;

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
import pl.kwi.springboot.commands.cards.CardsCommand;
import pl.kwi.springboot.db.entities.CardEntity;
import pl.kwi.springboot.db.entities.DeckEntity;
import pl.kwi.springboot.db.entities.WordEntity;
import pl.kwi.springboot.enums.LanguageEnum;
import pl.kwi.springboot.services.intf.CardService;
import pl.kwi.springboot.services.intf.DeckService;

@Controller
@RequestMapping(value="/cards")
public class CardsController {
	
	
	private static final String DEFAULT_DECK_NAME = "Talia numer ";
	private static final String CARDS_ATTRIBUTE = "cards";
	private static final int DEFAULT_CARDS_COUNT = 1;
	private static final int DEFAULT_CARD_NUMBER = 1;

	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private DeckService deckService;
	

	@RequestMapping
	public String display(
			@ModelAttribute("command") CardsCommand command,
			HttpSession session) {
						
		command.setDeckName(DEFAULT_DECK_NAME + getDeckDefaultId());
		command.setCurrentCardNumber(DEFAULT_CARD_NUMBER);
		command.setAllCardsCount(DEFAULT_CARDS_COUNT);
		session.setAttribute(CARDS_ATTRIBUTE, new ArrayList<CardEntity>());
		handlePrevious(command);
		handleDelete(command);
		
		return "cards/cards";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/next", method = RequestMethod.POST)
	public String next(
			@Validated @ModelAttribute("command") CardsCommand command,
			BindingResult bindingResult,
			HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			return "cards/cards";
		}
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		adjustCardsInSession(command, session, cards);		
		if(command.getCurrentCardNumber() == command.getAllCardsCount()) {
			handleNewCard(command, session);			
		} else {
			handleExistingCard(command, session, command.getCurrentCardNumber() + 1);
		}			
		handlePrevious(command);
		handleDelete(command);
		
		return "cards/cards";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/previous", method = RequestMethod.POST)
	public String previuos(
			@Validated @ModelAttribute("command") CardsCommand command,
			BindingResult bindingResult,
			HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			return "cards/cards";
		}
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		adjustCardsInSession(command, session, cards);
		handleExistingCard(command, session, command.getCurrentCardNumber() - 1);		
		handlePrevious(command);
		handleDelete(command);
				
		return "cards/cards";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String deleteCurrentCard(
			@ModelAttribute("command") CardsCommand command,			
			HttpSession session) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);		
		adjustCardsInSession(command, session, cards);		
		if(command.getCurrentCardNumber() == command.getAllCardsCount()) {
			deleteCardLast(command, session, cards);
		} else {
			deleteCardinMiddle(command, session, cards);
		}
		handlePrevious(command);
		handleDelete(command);
		
		return "cards/cards";
		
	}	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(
			@Validated @ModelAttribute("command") CardsCommand command,
			BindingResult bindingResult,
			HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			return "cards/cards";
		}
		
		DeckEntity deck = new DeckEntity(command.getDeckName());		
		deck = deckService.save(deck);
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		if(command.getAllCardsCount() != cards.size()) {			
			addNewCardToSessionAttribute(session, command);
		} else {
			updateCardInSessionAttribute(session, command, command.getCurrentCardNumber() - 1);
		}
			
		cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		for (CardEntity card : cards) {
			card.setDeck(deck);
			cardService.save(card);
		}		
		
		return "redirect:/home";
		
	}	
	
	private CardEntity createCard(CardsCommand command) {
		
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
	
	@SuppressWarnings("unchecked")
	private void addNewCardToSessionAttribute(HttpSession session, CardsCommand command) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		cards.add(createCard(command));	
		session.setAttribute(CARDS_ATTRIBUTE, cards);
		
	}
	
	@SuppressWarnings("unchecked")
	private void updateCardInSessionAttribute(HttpSession session, CardsCommand command, int index) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		cards.set(index, createCard(command));	
		session.setAttribute(CARDS_ATTRIBUTE, cards);
		
	}
	
	private void cleanNewCardCommand(CardsCommand command) {
		
		command.setPolishWord(null);
		command.setPolishSentence(null);
		
		command.setEnglishWord(null);
		command.setEnglishSentence(null);
		
		command.setRussianWord(null);
		command.setRussianSentence(null);
		
		command.setSpainWord(null);
		command.setSpainSentence(null);
		
	}
	
	@SuppressWarnings("unchecked")
	private void readNewCardCommand(CardsCommand command, HttpSession session, int index) {
		
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
	
	private void adjustCardsInSession(CardsCommand command, HttpSession session, List<CardEntity> cards) {
		
		if(command.getAllCardsCount() != (cards.size())) {
			addNewCardToSessionAttribute(session, command);
		}
		
	}
	
	private void handlePrevious(CardsCommand command) {
		
		if(DEFAULT_CARD_NUMBER == command.getCurrentCardNumber()) {
			command.setDisablePrevious(true);
		} else {
			command.setDisablePrevious(false);
		}
		
	}
	
	private void handleDelete(CardsCommand command) {
						
		if(command.getAllCardsCount() == DEFAULT_CARDS_COUNT) {
			command.setDisableDelete(true);
		} else {
			command.setDisableDelete(false);
		}
		
	}
	
	private void handleExistingCard(CardsCommand command, HttpSession session, int nextCardNumber) {
		
		updateCardInSessionAttribute(session, command, command.getCurrentCardNumber() - 1);
		readNewCardCommand(command, session, nextCardNumber - 1);
		command.setCurrentCardNumber(nextCardNumber);
		
	}
	
	private void handleNewCard(CardsCommand command, HttpSession session) {
		
		updateCardInSessionAttribute(session, command, command.getCurrentCardNumber() - 1);
		cleanNewCardCommand(command);
		command.setCurrentCardNumber(command.getCurrentCardNumber() + 1);
		command.setAllCardsCount(command.getAllCardsCount() + 1);
		
	}
	
	private void deleteCardLast(CardsCommand command, HttpSession session, List<CardEntity> cards) {
		
		readNewCardCommand(command, session, command.getCurrentCardNumber() - 2);
		command.setCurrentCardNumber(command.getCurrentCardNumber() - 1);	
		command.setAllCardsCount(command.getAllCardsCount() - 1);
		cards.remove(cards.size() -1);
		session.setAttribute(CARDS_ATTRIBUTE, cards);
		
	}
	
	private void deleteCardinMiddle(CardsCommand command, HttpSession session, List<CardEntity> cards) {
		
		readNewCardCommand(command, session, command.getCurrentCardNumber());
		command.setAllCardsCount(command.getAllCardsCount() - 1);
		cards.remove(command.getCurrentCardNumber() -1);
		session.setAttribute(CARDS_ATTRIBUTE, cards);
		
	}
	
	private long getDeckDefaultId() {
		
		Long maxId = deckService.getMaxId();
		if(maxId == null) {
			maxId = 0L;
		}
		maxId++;
		
		return maxId;
		
	}
	
	
}