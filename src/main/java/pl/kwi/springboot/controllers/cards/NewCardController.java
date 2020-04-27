package pl.kwi.springboot.controllers.cards;

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
import pl.kwi.springboot.db.repositories.CategoryRepository;

@Controller
public class NewCardController {
	
	
	private static final String DEFAULT_CATEGORY_1 = "1";
	
	@Autowired
	private CategoryRepository categoryRepository;
	

	@RequestMapping(value="/newCard")
	public String newCard(
			@ModelAttribute("command") NewCardCommand command) {
		
		if (StringUtils.isBlank(command.getSelectedCategory())) {
			command.setSelectedCategory(DEFAULT_CATEGORY_1);
		}		
		command.setCategories(categoryRepository.findAll());
		return "cards/newCard";
		
	}
	
	@RequestMapping(value="/saveCards", method = RequestMethod.POST)
	public String saveCards(
			@Validated @ModelAttribute("command") NewCardCommand command,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "cards/newCard";
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
		
		return "redirect:cards";
		
	}
	
	
}