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
		command.setCurrentCardNumber("1");
		command.setAllCardsCount("1");
		session.setAttribute("cards", new ArrayList<CardEntity>());
		
		return "cards/newCard";
		
	}
	
	@RequestMapping(value="/saveCards", method = RequestMethod.POST)
	public String saveCards(
			@Validated @ModelAttribute("command") NewCardCommand command,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "cards/newCard";
		}
		
		createCard(command);
		
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
		
		return "redirect:cards";
		
	}
	
	private void createCard(NewCardCommand command) {
		
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
		cardRepository.save(new CardEntity(category, words));
		
	}
	
	
}