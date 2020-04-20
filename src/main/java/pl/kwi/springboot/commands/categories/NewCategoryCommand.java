package pl.kwi.springboot.commands.categories;


public class NewCategoryCommand {
	
	
	private String category;
	private String redirect;
	

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getRedirect() {
		return redirect;
	}
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}	
	

}
