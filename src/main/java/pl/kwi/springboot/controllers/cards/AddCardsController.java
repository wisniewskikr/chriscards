package pl.kwi.springboot.controllers.cards;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.kwi.springboot.ajax.googlePlay.GooglePlayRequest;
import pl.kwi.springboot.ajax.googlePlay.GooglePlayResponse;
import pl.kwi.springboot.ajax.googleTranslate.GoogleTranslateRequest;
import pl.kwi.springboot.ajax.googleTranslate.GoogleTranslateResponse;
import pl.kwi.springboot.commands.cards.AddCardsCommand;
import pl.kwi.springboot.db.entities.CardEntity;
import pl.kwi.springboot.db.entities.DeckEntity;
import pl.kwi.springboot.db.entities.WordEntity;
import pl.kwi.springboot.db.repositories.CardRepository;
import pl.kwi.springboot.db.repositories.DeckRepository;
import pl.kwi.springboot.enums.LanguageEnum;
import pl.kwi.springboot.services.intf.Mp3Service;
import pl.kwi.springboot.services.intf.SpeechService;
import pl.kwi.springboot.services.intf.TranslationService;

@Controller
@RequestMapping(value="/cards")
public class AddCardsController {
	
	
	private static final String SPAIN_LANGUAGE_CODE = "es";
	private static final String RUSSIAN_LANGUAGE_CODE = "ru";
	private static final String ENGLISH_LANGUAGE_CODE = "en";
	private static final String POLISH_LANGUAGE_CODE = "pl";
	private static final String DEFAULT_DECK_NAME = "Talia numer ";
	private static final String CARDS_ATTRIBUTE = "cards";
	private static final int DEFAULT_CARDS_COUNT = 1;
	private static final int DEFAULT_CARD_NUMBER = 1;
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private DeckRepository deckRepository;
	
	@Autowired
	private TranslationService translationService;
	
	@Autowired
	private Mp3Service mp3Service;
	
	@Autowired
	private SpeechService speechService;
	

	@RequestMapping(value="/add")
	public String add(
			@ModelAttribute("command") AddCardsCommand command,
			HttpSession session) {
						
		command.setDeckName(DEFAULT_DECK_NAME + getDeckDefaultId());
		command.setCurrentCardNumber(DEFAULT_CARD_NUMBER);
		command.setAllCardsCount(DEFAULT_CARDS_COUNT);
		command.setDisablePrevious(true);
		session.setAttribute(CARDS_ATTRIBUTE, new ArrayList<CardEntity>());
		
		return "cards/addCards";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/next", method = RequestMethod.POST)
	public String next(
			@Validated @ModelAttribute("command") AddCardsCommand command,
			BindingResult bindingResult,
			HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			return "cards/addCards";
		}
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		adjustCardsInSession(command, session, cards);		
		if(command.getCurrentCardNumber() == command.getAllCardsCount()) {
			handleNewCard(command, session);			
		} else {
			handleExistingCard(command, session, command.getCurrentCardNumber() + 1);
		}			
		handlePreviousAndNext(command);
		
		return "cards/addCards";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/previous", method = RequestMethod.POST)
	public String previuos(
			@Validated @ModelAttribute("command") AddCardsCommand command,
			BindingResult bindingResult,
			HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			return "cards/addCards";
		}
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		adjustCardsInSession(command, session, cards);
		handleExistingCard(command, session, command.getCurrentCardNumber() - 1);		
		handlePreviousAndNext(command);
				
		return "cards/addCards";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String deleteCurrentCard(
			@Validated @ModelAttribute("command") AddCardsCommand command,
			BindingResult bindingResult,
			HttpSession session) {
		
		if(DEFAULT_CARD_NUMBER == command.getAllCardsCount()) {
			return "redirect:add";
		}
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);		
		adjustCardsInSession(command, session, cards);		
		if(command.getCurrentCardNumber() == command.getAllCardsCount()) {
			deleteCardLast(command, session, cards);
		} else {
			deleteCardinMiddle(command, session, cards);
		}
		handlePreviousAndNext(command);
		
		return "cards/addCards";
		
	}	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(
			@Validated @ModelAttribute("command") AddCardsCommand command,
			BindingResult bindingResult,
			HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			return "cards/addCards";
		}
		
		DeckEntity deck = new DeckEntity(command.getDeckName());
		deck = deckRepository.save(deck);
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		if(command.getAllCardsCount() != cards.size()) {			
			addNewCardToSessionAttribute(session, command);
		} else {
			updateCardInSessionAttribute(session, command, command.getCurrentCardNumber() - 1);
		}
			
		cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		for (CardEntity card : cards) {
			card.setDeck(deck);
			cardRepository.save(card);
		}		
		
		return "redirect:/cards";
		
	}		
	
	@RequestMapping(value="/translate", method=RequestMethod.POST)
	public @ResponseBody GoogleTranslateResponse translateAjax(@Valid @RequestBody GoogleTranslateRequest request, BindingResult result) {
		
		GoogleTranslateResponse response = new GoogleTranslateResponse();
		
		if(result.hasErrors()) {
			response.setStatus("FAIL");
			response.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return response;
		}		
		
		response.setStatus("SUCCESS");		
		response.setEnglishWord(translationService.getTranslation(request.getPolishWord(), POLISH_LANGUAGE_CODE, ENGLISH_LANGUAGE_CODE));
		response.setEnglishSentence(translationService.getTranslation(request.getPolishSentence(), POLISH_LANGUAGE_CODE, ENGLISH_LANGUAGE_CODE));
		response.setRussianWord(translationService.getTranslation(request.getPolishWord(), POLISH_LANGUAGE_CODE, RUSSIAN_LANGUAGE_CODE));
		response.setRussianSentence(translationService.getTranslation(request.getPolishSentence(), POLISH_LANGUAGE_CODE, RUSSIAN_LANGUAGE_CODE));
		response.setSpainWord(translationService.getTranslation(request.getPolishWord(), POLISH_LANGUAGE_CODE, SPAIN_LANGUAGE_CODE));
		response.setSpainSentence(translationService.getTranslation(request.getPolishSentence(), POLISH_LANGUAGE_CODE, SPAIN_LANGUAGE_CODE));
		return response;
		
	}
	
	@RequestMapping(value="/play", method=RequestMethod.POST)
	public @ResponseBody GooglePlayResponse playAjax(@Valid @RequestBody GooglePlayRequest request, BindingResult result) throws Exception {
		
		GooglePlayResponse response = new GooglePlayResponse();
		
		if(result.hasErrors()) {
			response.setStatus("FAIL");
			response.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return response;
		}		
		
		speechService.createSpeechMp3(request.getText(), "en-US");
		mp3Service.play("tmp.mp3");
		
		return response;
		
	}
	
	private CardEntity createCard(AddCardsCommand command) {
		
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
	private void addNewCardToSessionAttribute(HttpSession session, AddCardsCommand command) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		cards.add(createCard(command));	
		session.setAttribute(CARDS_ATTRIBUTE, cards);
		
	}
	
	@SuppressWarnings("unchecked")
	private void updateCardInSessionAttribute(HttpSession session, AddCardsCommand command, int index) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		cards.set(index, createCard(command));	
		session.setAttribute(CARDS_ATTRIBUTE, cards);
		
	}
	
	private void cleanNewCardCommand(AddCardsCommand command) {
		
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
	private void readNewCardCommand(AddCardsCommand command, HttpSession session, int index) {
		
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
	
	private void adjustCardsInSession(AddCardsCommand command, HttpSession session, List<CardEntity> cards) {
		
		if(command.getAllCardsCount() != (cards.size())) {
			addNewCardToSessionAttribute(session, command);
		}
		
	}
	
	private void handlePreviousAndNext(AddCardsCommand command) {
		
		if(DEFAULT_CARD_NUMBER == command.getCurrentCardNumber()) {
			command.setDisablePrevious(true);
		} else {
			command.setDisablePrevious(false);
		}
		
		if(command.getCurrentCardNumber() == command.getAllCardsCount()) {
			command.setVisibleNext(false);
		} else {
			command.setVisibleNext(true);
		}
		
	}
	
	private void handleExistingCard(AddCardsCommand command, HttpSession session, int nextCardNumber) {
		
		updateCardInSessionAttribute(session, command, command.getCurrentCardNumber() - 1);
		readNewCardCommand(command, session, nextCardNumber - 1);
		command.setCurrentCardNumber(nextCardNumber);
		
	}
	
	private void handleNewCard(AddCardsCommand command, HttpSession session) {
		
		updateCardInSessionAttribute(session, command, command.getCurrentCardNumber() - 1);
		cleanNewCardCommand(command);
		command.setCurrentCardNumber(command.getCurrentCardNumber() + 1);
		command.setAllCardsCount(command.getAllCardsCount() + 1);
		
	}
	
	private void deleteCardLast(AddCardsCommand command, HttpSession session, List<CardEntity> cards) {
		
		readNewCardCommand(command, session, command.getCurrentCardNumber() - 2);
		command.setCurrentCardNumber(command.getCurrentCardNumber() - 1);	
		command.setAllCardsCount(command.getAllCardsCount() - 1);
		cards.remove(cards.size() -1);
		session.setAttribute(CARDS_ATTRIBUTE, cards);
		
	}
	
	private void deleteCardinMiddle(AddCardsCommand command, HttpSession session, List<CardEntity> cards) {
		
		readNewCardCommand(command, session, command.getCurrentCardNumber());
		command.setAllCardsCount(command.getAllCardsCount() - 1);
		cards.remove(command.getCurrentCardNumber() -1);
		session.setAttribute(CARDS_ATTRIBUTE, cards);
		
	}
	
	private long getDeckDefaultId() {
		
		Long maxId = deckRepository.getMaxId();
		if(maxId == null) {
			maxId = 0L;
		}
		maxId++;
		
		return maxId;
		
	}
	
	
}