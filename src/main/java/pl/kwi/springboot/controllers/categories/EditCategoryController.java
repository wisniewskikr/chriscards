package pl.kwi.springboot.controllers.categories;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kwi.springboot.commands.categories.EditCategoryCommand;
import pl.kwi.springboot.db.entities.CategoryEntity;
import pl.kwi.springboot.db.repositories.CategoryRepository;

@Controller
public class EditCategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping(value="/editCategory")
	public String displayEditCategory(
			@ModelAttribute("command") EditCategoryCommand command) {
		
		command.setCategories(categoryRepository.findAllWithoutDefault());
		
		return "categories/editCategory";
		
	}
	
	@RequestMapping(value="/editCategory", method = RequestMethod.POST)
	public String editCategory(
			@ModelAttribute("command") EditCategoryCommand command,
			BindingResult bindingResult) {
		
		if (StringUtils.isBlank(command.getSelectedCategory())) {
			command.setCategories(categoryRepository.findAllWithoutDefault());
			bindingResult.rejectValue("selectedCategory", null, "Proszę wybrać kategorię do edycji");			
			return "categories/editCategory";
		}
		
		if (StringUtils.isBlank(command.getNewCategory())) {
			command.setCategories(categoryRepository.findAllWithoutDefault());
			bindingResult.rejectValue("newCategory", null, "Proszę podać nazwę nowej kategorii");			
			return "categories/editCategory";
		}	
		
		CategoryEntity category = categoryRepository.findById(Long.valueOf(command.getSelectedCategory())).get();
		category.setName(command.getNewCategory());
		categoryRepository.save(category);
		
		return "redirect:categories";
		
	}
	
}