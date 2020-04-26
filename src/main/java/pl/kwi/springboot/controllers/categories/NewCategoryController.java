package pl.kwi.springboot.controllers.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
			@ModelAttribute("command") NewCategoryCommand command,
			BindingResult bindingResult) {
		
		CategoryEntity category;
		try {
			category = categoryRepository.save(new CategoryEntity(command.getCategory()));
		} catch (DataIntegrityViolationException e) {
			bindingResult.rejectValue("category", null, "Taka kategoria już istnieje");			
			return "categories/newCategory";
		}
		
		return "redirect:" + command.getRedirect() + "?selectedCategory=" + category.getId();
		
	}
	
}