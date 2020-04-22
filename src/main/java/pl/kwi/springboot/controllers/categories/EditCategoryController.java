package pl.kwi.springboot.controllers.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kwi.springboot.commands.categories.EditCategoryCommand;
import pl.kwi.springboot.db.repositories.CategoryRepository;

@Controller
public class EditCategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping(value="/editCategory")
	public String displayEditCategory(
			@ModelAttribute("command") EditCategoryCommand command) {
		
		return "categories/editCategory";
		
	}
	
	@RequestMapping(value="/editCategory", method = RequestMethod.POST)
	public String editCategory(
			@ModelAttribute("command") EditCategoryCommand command,
			BindingResult bindingResult) {
		
		return "redirect:categories";
		
	}
	
}