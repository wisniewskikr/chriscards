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
			return "cards/newCard";
		}
		
		addNewCardToSessionAttribute(session, command);
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
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
			return "cards/newCard";
		}
		
		return "redirect:cards";
		
	}
	
	@RequestMapping(value="/previousCard", method = RequestMethod.POST)
	public String previuosCard(
			@Validated @ModelAttribute("command") NewCardCommand command,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "cards/newCard";
		}
		
		return "redirect:cards";
		
	}
	
	@RequestMapping(value="/nextCard", method = RequestMethod.POST)
	public String nextCard(
			@Validated @ModelAttribute("command") NewCardCommand command,
			BindingResult bindingResult,
			HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			return "cards/newCard";
		}
		
		command.setCategories(categoryRepository.findAll());		
		String currentCardNumber = String.valueOf(Integer.valueOf(command.getCurrentCardNumber()) + 1);
		String cardsCount = String.valueOf(Integer.valueOf(command.getAllCardsCount()) + 1);
		handleCardNumeration(currentCardNumber, cardsCount, command);
		addNewCardToSessionAttribute(session, command);
		
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
	
	private void handleCardNumeration(String currentCardNumber, String cardsCount, NewCardCommand command) {
		
		command.setCurrentCardNumber(currentCardNumber);
		command.setAllCardsCount(cardsCount);
		
	}
	
	@SuppressWarnings("unchecked")
	private void addNewCardToSessionAttribute(HttpSession session, NewCardCommand command) {
		
		List<CardEntity> cards = (List<CardEntity>)session.getAttribute(CARDS_ATTRIBUTE);
		cards.add(createCard(command));	
		session.setAttribute(CARDS_ATTRIBUTE, cards);
		
	}
	
	
}