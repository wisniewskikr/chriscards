package pl.kwi.springboot.commands.categories;

import pl.kwi.springboot.db.entities.CategoryEntity;

public class DeleteCategoryCommand {
	
	
	private Iterable<CategoryEntity> categories;
	private String selectedCategory;
	
	
	public Iterable<CategoryEntity> getCategories() {
		return categories;
	}
	public void setCategories(Iterable<CategoryEntity> categories) {
		this.categories = categories;
	}
	
	public String getSelectedCategory() {
		return selectedCategory;
	}
	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}
	
	
}