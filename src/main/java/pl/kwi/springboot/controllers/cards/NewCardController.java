package pl.kwi.springboot.controllers.cards;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kwi.springboot.commands.cards.NewCardCommand;
import pl.kwi.springboot.controllers.enums.LanguageEnum;
import pl.kwi.springboot.db.entities.CardEntity;
import pl.kwi.springboot.db.entities.CategoryEntity;
import pl.kwi.springboot.db.entities.WordEntity;
import pl.kwi.springboot.db.repositories.CardRepository;
import pl.kwi.springboot.db.repositories.CategoryRepository;

@Controller
public class NewCardController {
	
	
	private static final String CARDS_ATTRIBUTE = "cards";
	private static final String DEFAULT_CARDS_COUNT = "1";
	private static final String DEFAULT_CARD_NUMBER = "1";
	private static final String DEFAULT_CATEGORY_1 = "1";
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CardRepository cardRepository;
	

	@RequestMapping(value="/newCard")
	public String newCard(
			@ModelAttribute("command") NewCardCommand command,
			HttpSession session) {
		
		if (StringUtils.isBlank(command.getSelectedCategory())) {
			command.setSelectedCategory(DEFAULT_CATEGORY_1);
		}		
		command.setCategories(categoryRepository.findAll());
		command.setCurrentCardNumber(DEFAULT_CARD_NUMBER);
		command.setAllCardsCount(DEFAULT_CARDS_COUNT);
		command.setDisablePrevious(true);
		session.setAttribute(CARDS_ATTRIBUTE, new ArrayList<CardEntity>());
		
		return "cards/newCard";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/saveCards", method = RequestMethod.POST)
	public String saveCards(
			@Validated @ModelAttribute("command") NewCardCommand command,
			BindingResult bindingResult,
			HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			command.setCategories(categoryRepository.findAll());
			return "cards/newCard";
		}
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		if(!command.getAllCardsCount().equals(String.valueOf(cards.size()))) {			
			addNewCardToSessionAttribute(session, command);
		} else {
			updateCardInSessionAttribute(session, command, Integer.valueOf(command.getCurrentCardNumber()) - 1);
		}
			
		cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		for (CardEntity card : cards) {
			cardRepository.save(card);
		}		
		
		return "redirect:cards";
		
	}
		
	@RequestMapping(value="/deleteCurrentCard", method = RequestMethod.POST)
	public String deleteCurrentCard(
			@Validated @ModelAttribute("command") NewCardCommand command,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			command.setCategories(categoryRepository.findAll());
			return "cards/newCard";
		}
		
		return "redirect:cards";
		
	}
	
	@RequestMapping(value="/previousCard", method = RequestMethod.POST)
	public String previuosCard(
			@Validated @ModelAttribute("command") NewCardCommand command,
			BindingResult bindingResult,
			HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			command.setCategories(categoryRepository.findAll());
			return "cards/newCard";
		}
		
		command.setCategories(categoryRepository.findAll());
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
				
		if(!command.getAllCardsCount().equals(String.valueOf(cards.size()))) {			
			addNewCardToSessionAttribute(session, command);
			readNewCardCommand(command, session, Integer.valueOf(command.getCurrentCardNumber()) - 2);
			command.setCurrentCardNumber(String.valueOf(Integer.valueOf(command.getCurrentCardNumber()) - 1));			
		} else {		
			updateCardInSessionAttribute(session, command, Integer.valueOf(command.getCurrentCardNumber()) - 1);
			readNewCardCommand(command, session, Integer.valueOf(command.getCurrentCardNumber()) - 2);
			command.setCurrentCardNumber(String.valueOf(Integer.valueOf(command.getCurrentCardNumber()) - 1));			
		}
		
		if("1".equals(command.getCurrentCardNumber())) {
			command.setDisablePrevious(true);
		} else {
			command.setDisablePrevious(false);
		}
				
		return "cards/newCard";
		
	}
	
	@RequestMapping(value="/nextCard", method = RequestMethod.POST)
	public String nextCard(
			@Validated @ModelAttribute("command") NewCardCommand command,
			BindingResult bindingResult,
			HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			command.setCategories(categoryRepository.findAll());
			return "cards/newCard";
		}
		
		command.setCategories(categoryRepository.findAll());
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		
		if(!command.getAllCardsCount().equals(String.valueOf(cards.size()))) {
			addNewCardToSessionAttribute(session, command);
			cleanNewCardCommand(command);
			command.setCurrentCardNumber(String.valueOf(Integer.valueOf(command.getCurrentCardNumber()) + 1));
			command.setAllCardsCount(String.valueOf(Integer.valueOf(command.getAllCardsCount()) + 1));			
		} else {
			updateCardInSessionAttribute(session, command, Integer.valueOf(command.getCurrentCardNumber()) - 1);
			readNewCardCommand(command, session, Integer.valueOf(command.getCurrentCardNumber()));
			command.setCurrentCardNumber(String.valueOf(Integer.valueOf(command.getCurrentCardNumber()) + 1));
		}	
		
		if("1".equals(command.getCurrentCardNumber())) {
			command.setDisablePrevious(true);
		} else {
			command.setDisablePrevious(false);
		}
		
		return "cards/newCard";
		
	}
	
	private CardEntity createCard(NewCardCommand command) {
		
		CategoryEntity category = categoryRepository.findById(Long.valueOf(command.getSelectedCategory())).get();
		List<WordEntity> words = new ArrayList<WordEntity>();
		WordEntity word;
		word = new WordEntity(command.getPolishWord(), command.getPolishWordPrononciation(), command.getPolishSentence(), command.getPolishSentencePrononciation(), LanguageEnum.POLISH);
		words.add(word);
		word = new WordEntity(command.getEnglishWord(), command.getEnglishWordPrononciation(), command.getEnglishSentence(), command.getEnglishSentencePrononciation(), LanguageEnum.ENGLISH);
		words.add(word);
		word = new WordEntity(command.getRussianWord(), command.getRussianWordPrononciation(), command.getRussianSentence(), command.getRussianSentencePrononciation(), LanguageEnum.RUSSIAN);
		words.add(word);
		word = new WordEntity(command.getSpainWord(), command.getSpainWordPrononciation(), command.getSpainSentence(), command.getSpainSentencePrononciation(), LanguageEnum.SPAIN);
		words.add(word);
		word = new WordEntity(command.getGermanWord(), command.getGermanWordPrononciation(), command.getGermanSentence(), command.getGermanSentencePrononciation(), LanguageEnum.GERMAN);
		words.add(word);
		return new CardEntity(category, words);
		
	}
	
	@SuppressWarnings("unchecked")
	private void addNewCardToSessionAttribute(HttpSession session, NewCardCommand command) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		cards.add(createCard(command));	
		session.setAttribute(CARDS_ATTRIBUTE, cards);
		
	}
	
	@SuppressWarnings("unchecked")
	private void updateCardInSessionAttribute(HttpSession session, NewCardCommand command, int index) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		cards.set(index, createCard(command));	
		session.setAttribute(CARDS_ATTRIBUTE, cards);
		
	}
	
	private void cleanNewCardCommand(NewCardCommand command) {
		
		command.setPolishWord(null);
		command.setPolishWordPrononciation(null);
		command.setPolishSentence(null);
		command.setPolishSentencePrononciation(null);
		
		command.setEnglishWord(null);
		command.setEnglishWordPrononciation(null);
		command.setEnglishSentence(null);
		command.setEnglishSentencePrononciation(null);
		
		command.setRussianWord(null);
		command.setRussianWordPrononciation(null);
		command.setRussianSentence(null);
		command.setRussianSentencePrononciation(null);
		
		command.setSpainWord(null);
		command.setSpainWordPrononciation(null);
		command.setSpainSentence(null);
		command.setSpainSentencePrononciation(null);
		
		command.setGermanWord(null);
		command.setGermanWordPrononciation(null);
		command.setGermanSentence(null);
		command.setGermanSentencePrononciation(null);
		
	}
	
	private void readNewCardCommand(NewCardCommand command, HttpSession session, int index) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		CardEntity card = cards.get(index);
		
		command.setSelectedCategory(String.valueOf(card.getCategory().getId()));
		
		WordEntity polishWord = card.getWords().get(0);		
		command.setPolishWord(polishWord.getWord());
		command.setPolishWordPrononciation(polishWord.getWordPrononciation());
		command.setPolishSentence(polishWord.getSentence());
		command.setPolishSentencePrononciation(polishWord.getSentencePronociation());
		
		WordEntity englishhWord = card.getWords().get(1);
		command.setEnglishWord(englishhWord.getWord());
		command.setEnglishWordPrononciation(englishhWord.getWordPrononciation());
		command.setEnglishSentence(englishhWord.getSentence());
		command.setEnglishSentencePrononciation(englishhWord.getSentencePronociation());
		
		WordEntity russianhWord = card.getWords().get(2);
		command.setRussianWord(russianhWord.getWord());
		command.setRussianWordPrononciation(russianhWord.getWordPrononciation());
		command.setRussianSentence(russianhWord.getSentence());
		command.setRussianSentencePrononciation(russianhWord.getSentencePronociation());
		
		WordEntity spainhWord = card.getWords().get(3);
		command.setSpainWord(spainhWord.getWord());
		command.setSpainWordPrononciation(spainhWord.getWordPrononciation());
		command.setSpainSentence(spainhWord.getSentence());
		command.setSpainSentencePrononciation(spainhWord.getSentencePronociation());
		
		WordEntity germanhWord = card.getWords().get(4);
		command.setGermanWord(germanhWord.getWord());
		command.setGermanWordPrononciation(germanhWord.getWordPrononciation());
		command.setGermanSentence(germanhWord.getSentence());
		command.setGermanSentencePrononciation(germanhWord.getSentencePronociation());
		
	}
	
	
}