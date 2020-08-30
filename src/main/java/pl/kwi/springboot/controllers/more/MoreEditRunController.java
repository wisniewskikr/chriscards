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
import pl.kwi.springboot.services.intf.CardService;
import pl.kwi.springboot.services.intf.DeckService;

@Controller
@RequestMapping(value="/more/edit/run")
public class MoreEditRunController extends AbstrCheckboxPaginationController {
	
	// TODO
	private static final String CARDS_ATTRIBUTE = "cards";
	private static final int DEFAULT_CARDS_COUNT = 1;
	private static final int DEFAULT_CARD_NUMBER = 1;	
	
	@Autowired
	private CardService cardService;
	
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
		session.setAttribute(CARDS_ATTRIBUTE, deck.getCards());
		
		readNewCardCommand(command, session, 0);			
		handlePrevious(command);
		handleDelete(command);
		
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
		handlePrevious(command);
		handleDelete(command);
		
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
		handlePrevious(command);
		handleDelete(command);
				
		return "more/moreEditRun";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String deleteCurrentCard(
			@Validated @ModelAttribute("command") MoreEditRunCommand command,
			BindingResult bindingResult,
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
		
		return "more/moreEditRun";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(
			@Validated @ModelAttribute("command") MoreEditRunCommand command,
			BindingResult bindingResult,
			HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			return "more/moreEditRun";
		}
		
		DeckEntity deck = deckService.findById(command.getSelectedItem());
		
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
		
		return "redirect:/more/edit/list";
		
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
		word = new WordEntity( command.getSpainWord(), command.getSpainSentence(), LanguageEnum.SPAIN);
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
		cards.set(index, updateCard(cards.get(index), command));	
		session.setAttribute(CARDS_ATTRIBUTE, cards);
		
	}
	
	private CardEntity updateCard(CardEntity card, MoreEditRunCommand command) {
		
		card.getWords().get(0).setWord(command.getPolishWord());
		card.getWords().get(0).setWord(command.getPolishSentence());
		
		card.getWords().get(1).setWord(command.getEnglishWord());
		card.getWords().get(1).setWord(command.getEnglishSentence());
		
		card.getWords().get(2).setWord(command.getRussianWord());
		card.getWords().get(2).setWord(command.getRussianSentence());
		
		card.getWords().get(3).setWord(command.getSpainWord());
		card.getWords().get(3).setWord(command.getSpainSentence());
		
		return card;
		
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
	
	private void handlePrevious(MoreEditRunCommand command) {
		
		if(DEFAULT_CARD_NUMBER == command.getCurrentCardNumber()) {
			command.setDisablePrevious(true);
		} else {
			command.setDisablePrevious(false);
		}
		
	}
	
	private void handleDelete(MoreEditRunCommand command) {
		
		if(command.getAllCardsCount() == DEFAULT_CARDS_COUNT) {
			command.setDisableDelete(true);
		} else {
			command.setDisableDelete(false);
		}
		
	}
	
	private void deleteCardLast(MoreEditRunCommand command, HttpSession session, List<CardEntity> cards) {
		
		readNewCardCommand(command, session, command.getCurrentCardNumber() - 2);
		command.setCurrentCardNumber(command.getCurrentCardNumber() - 1);	
		command.setAllCardsCount(command.getAllCardsCount() - 1);
		cards.remove(cards.size() -1);
		session.setAttribute(CARDS_ATTRIBUTE, cards);
		
	}
	
	private void deleteCardinMiddle(MoreEditRunCommand command, HttpSession session, List<CardEntity> cards) {
		
		readNewCardCommand(command, session, command.getCurrentCardNumber());
		command.setAllCardsCount(command.getAllCardsCount() - 1);
		cards.remove(command.getCurrentCardNumber() -1);
		session.setAttribute(CARDS_ATTRIBUTE, cards);
		
	}

	
}