package pl.kwi.springboot.commands.cards;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import pl.kwi.springboot.db.entities.CategoryEntity;

public class NewCardCommand {
	
	
	private Iterable<CategoryEntity> categories;
	@NotNull(message = "Proszę wybrać jakąś wartość")
	private String selectedCategory;
	//@NotBlank(message = "To pole nie może być puste")
	private String polishWord;
	private String polishWordPrononciation;
	//@NotBlank(message = "To pole nie może być puste")
	private String polishSentence;
	private String polishSentencePrononciation;
	//@NotBlank(message = "To pole nie może być puste")
	private String englishWord;
	private String englishWordPrononciation;
	//@NotBlank(message = "To pole nie może być puste")
	private String englishSentence;
	private String englishSentencePrononciation;
	//@NotBlank(message = "To pole nie może być puste")
	private String russianWord;
	private String russianWordPrononciation;
	//@NotBlank(message = "To pole nie może być puste")
	private String russianSentence;
	private String russianSentencePrononciation;
	//@NotBlank(message = "To pole nie może być puste")
	private String spainWord;
	private String spainWordPrononciation;
	//@NotBlank(message = "To pole nie może być puste")
	private String spainSentence;
	private String spainSentencePrononciation;
	//@NotBlank(message = "To pole nie może być puste")
	private String germanWord;
	private String germanWordPrononciation;
	//@NotBlank(message = "To pole nie może być puste")
	private String germanSentence;
	private String germanSentencePrononciation;
	private String currentCardNumber;
	private String allCardsCount;
	
	
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
	
	public String getPolishWordPrononciation() {
		return polishWordPrononciation;
	}
	public void setPolishWordPrononciation(String polishWordPrononciation) {
		this.polishWordPrononciation = polishWordPrononciation;
	}
	
	public String getPolishSentence() {
		return polishSentence;
	}
	public void setPolishSentence(String polishSentence) {
		this.polishSentence = polishSentence;
	}
	
	public String getPolishSentencePrononciation() {
		return polishSentencePrononciation;
	}
	public void setPolishSentencePrononciation(String polishSentencePrononciation) {
		this.polishSentencePrononciation = polishSentencePrononciation;
	}
	
	public String getEnglishWord() {
		return englishWord;
	}
	public void setEnglishWord(String englishWord) {
		this.englishWord = englishWord;
	}
	public String getEnglishWordPrononciation() {
		return englishWordPrononciation;
	}
	
	public void setEnglishWordPrononciation(String englishWordPrononciation) {
		this.englishWordPrononciation = englishWordPrononciation;
	}
	public String getEnglishSentence() {
		return englishSentence;
	}
	public void setEnglishSentence(String englishSentence) {
		this.englishSentence = englishSentence;
	}
	
	public String getEnglishSentencePrononciation() {
		return englishSentencePrononciation;
	}
	public void setEnglishSentencePrononciation(String englishSentencePrononciation) {
		this.englishSentencePrononciation = englishSentencePrononciation;
	}
	
	public String getRussianWord() {
		return russianWord;
	}
	public void setRussianWord(String russianWord) {
		this.russianWord = russianWord;
	}
	
	public String getRussianWordPrononciation() {
		return russianWordPrononciation;
	}
	public void setRussianWordPrononciation(String russianWordPrononciation) {
		this.russianWordPrononciation = russianWordPrononciation;
	}
	
	public String getRussianSentence() {
		return russianSentence;
	}
	public void setRussianSentence(String russianSentence) {
		this.russianSentence = russianSentence;
	}
	
	public String getRussianSentencePrononciation() {
		return russianSentencePrononciation;
	}
	public void setRussianSentencePrononciation(String russianSentencePrononciation) {
		this.russianSentencePrononciation = russianSentencePrononciation;
	}
	
	public String getSpainWord() {
		return spainWord;
	}
	public void setSpainWord(String spainWord) {
		this.spainWord = spainWord;
	}
	
	public String getSpainWordPrononciation() {
		return spainWordPrononciation;
	}
	public void setSpainWordPrononciation(String spainWordPrononciation) {
		this.spainWordPrononciation = spainWordPrononciation;
	}
	
	public String getSpainSentence() {
		return spainSentence;
	}
	public void setSpainSentence(String spainSentence) {
		this.spainSentence = spainSentence;
	}
	
	public String getSpainSentencePrononciation() {
		return spainSentencePrononciation;
	}
	public void setSpainSentencePrononciation(String spainSentencePrononciation) {
		this.spainSentencePrononciation = spainSentencePrononciation;
	}
	
	public String getGermanWord() {
		return germanWord;
	}
	public void setGermanWord(String germanWord) {
		this.germanWord = germanWord;
	}
	
	public String getGermanWordPrononciation() {
		return germanWordPrononciation;
	}
	public void setGermanWordPrononciation(String germanWordPrononciation) {
		this.germanWordPrononciation = germanWordPrononciation;
	}
	
	public String getGermanSentence() {
		return germanSentence;
	}
	public void setGermanSentence(String germanSentence) {
		this.germanSentence = germanSentence;
	}
	
	public String getGermanSentencePrononciation() {
		return germanSentencePrononciation;
	}
	public void setGermanSentencePrononciation(String germanSentencePrononciation) {
		this.germanSentencePrononciation = germanSentencePrononciation;
	}
	
	public String getCurrentCardNumber() {
		return currentCardNumber;
	}
	public void setCurrentCardNumber(String currentCardNumber) {
		this.currentCardNumber = currentCardNumber;
	}
	
	public String getAllCardsCount() {
		return allCardsCount;
	}
	public void setAllCardsCount(String allCardsCount) {
		this.allCardsCount = allCardsCount;
	}
		
	
}
