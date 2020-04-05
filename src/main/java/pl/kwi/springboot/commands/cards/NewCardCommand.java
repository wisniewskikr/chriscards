package pl.kwi.springboot.commands.cards;

import pl.kwi.springboot.db.entities.CategoryEntity;

public class NewCardCommand {
	
	
	private Iterable<CategoryEntity> categories;

	
	public Iterable<CategoryEntity> getCategories() {
		return categories;
	}
	public void setCategories(Iterable<CategoryEntity> categories) {
		this.categories = categories;
	}

	
}
