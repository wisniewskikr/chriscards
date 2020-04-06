package pl.kwi.springboot.controllers.categories;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.categories.CategoriesCommand;

@Controller
public class CategoriesController {

	@RequestMapping(value="/categories")
	public String cards(
			@ModelAttribute("command") CategoriesCommand command) {
		return "categories/categories";
	}
	
}