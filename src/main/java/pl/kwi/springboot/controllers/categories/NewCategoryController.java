package pl.kwi.springboot.controllers.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.categories.NewCategoryCommand;
import pl.kwi.springboot.db.entities.CategoryEntity;
import pl.kwi.springboot.db.repositories.CategoryRepository;

@Controller
public class NewCategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping(value="/newCategory")
	public String newCategory(
			@ModelAttribute("command") NewCategoryCommand command) {
		
		return "categories/newCategory";
		
	}
	
	@RequestMapping(value="/addCategory")
	public String addCategory(
			@ModelAttribute("command") NewCategoryCommand command) {
		
		categoryRepository.save(new CategoryEntity(command.getCategory()));
		return "categories/categories";
		
	}
	
}