package pl.kwi.springboot.commands.categories;

import pl.kwi.springboot.db.entities.CategoryEntity;

public class EditCategoryCommand {
	
	
	private Iterable<CategoryEntity> categories;
	private String selectedCategory;
	private String newCategory;
	
	
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
	
	public String getNewCategory() {
		return newCategory;
	}
	public void setNewCategory(String newCategory) {
		this.newCategory = newCategory;
	}
		
	
}
