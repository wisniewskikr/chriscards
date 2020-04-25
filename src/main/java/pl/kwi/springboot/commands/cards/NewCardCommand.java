package pl.kwi.springboot.commands.cards;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import pl.kwi.springboot.db.entities.CategoryEntity;

public class NewCardCommand {
	
	
	private Iterable<CategoryEntity> categories;
	@NotNull(message = "Proszę wybrać jakąś wartość")
	private String selectedCategory;
	@NotBlank(message = "To pole nie może być puste")
	private String polishWord;
	@NotBlank(message = "To pole nie może być puste")
	private String polishSentence;
	@NotBlank(message = "To pole nie może być puste")
	private String englishWord;
	@NotBlank(message = "To pole nie może być puste")
	private String englishSentence;
	@NotBlank(message = "To pole nie może być puste")
	private String russianWord;
	@NotBlank(message = "To pole nie może być puste")
	private String russianSentence;
	@NotBlank(message = "To pole nie może być puste")
	private String spainWord;
	@NotBlank(message = "To pole nie może być puste")
	private String spainSentence;
	@NotBlank(message = "To pole nie może być puste")
	private String germanWord;
	@NotBlank(message = "To pole nie może być puste")
	private String germanSentence;

	
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
	
	public String getPolishWord() {
		return polishWord;
	}
	public void setPolishWord(String polishWord) {
		this.polishWord = polishWord;
	}
	
	public String getPolishSentence() {
		return polishSentence;
	}
	public void setPolishSentence(String polishSentence) {
		this.polishSentence = polishSentence;
	}
	
	public String getEnglishWord() {
		return englishWord;
	}
	public void setEnglishWord(String englishWord) {
		this.englishWord = englishWord;
	}
	
	public String getEnglishSentence() {
		return englishSentence;
	}
	public void setEnglishSentence(String englishSentence) {
		this.englishSentence = englishSentence;
	}
	
	public String getRussianWord() {
		return russianWord;
	}
	public void setRussianWord(String russianWord) {
		this.russianWord = russianWord;
	}
	public String getRussianSentence() {
		return russianSentence;
	}
	
	public void setRussianSentence(String russianSentence) {
		this.russianSentence = russianSentence;
	}
	public String getSpainWord() {
		return spainWord;
	}
	
	public void setSpainWord(String spainWord) {
		this.spainWord = spainWord;
	}
	public String getSpainSentence() {
		return spainSentence;
	}
	
	public void setSpainSentence(String spainSentence) {
		this.spainSentence = spainSentence;
	}
	public String getGermanWord() {
		return germanWord;
	}
	
	public void setGermanWord(String germanWord) {
		this.germanWord = germanWord;
	}
	public String getGermanSentence() {
		return germanSentence;
	}
	public void setGermanSentence(String germanSentence) {
		this.germanSentence = germanSentence;
	}	
	
	
}
