package pl.kwi.springboot.controllers.categories;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kwi.springboot.commands.categories.DeleteCategoryCommand;
import pl.kwi.springboot.db.entities.CategoryEntity;
import pl.kwi.springboot.db.repositories.CategoryRepository;

@Controller
public class DeleteCategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping(value="/deleteCategory")
	public String displayDeleteCategory(
			@ModelAttribute("command") DeleteCategoryCommand command) {
		
		command.setCategories(categoryRepository.findAllWithoutDefault());
		
		return "categories/deleteCategory";
		
	}
	
	@RequestMapping(value="/deleteCategory", method = RequestMethod.POST)
	public String deleteCategory(
			@ModelAttribute("command") DeleteCategoryCommand command,
			BindingResult bindingResult) {
		
		if (StringUtils.isBlank(command.getSelectedCategory())) {
			command.setCategories(categoryRepository.findAllWithoutDefault());
			bindingResult.rejectValue("selectedCategory", null, "Proszę wybrać kategorię do usunięcia");			
			return "categories/deleteCategory";
		}
		
		CategoryEntity category = categoryRepository.findById(Long.valueOf(command.getSelectedCategory())).get();
		categoryRepository.delete(category);
		
		return "redirect:categories";
		
	}
	
}