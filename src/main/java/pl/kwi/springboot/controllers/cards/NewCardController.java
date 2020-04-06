package pl.kwi.springboot.controllers.cards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.cards.NewCardCommand;
import pl.kwi.springboot.db.repositories.CategoryRepository;

@Controller
public class NewCardController {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping(value="/newCard")
	public String newCard(
			@ModelAttribute("command") NewCardCommand command) {
		
		command.setCategories(categoryRepository.findAll());
		return "cards/newCard";
		
	}
	
}