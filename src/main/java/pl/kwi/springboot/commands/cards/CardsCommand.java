package pl.kwi.springboot.commands.cards;

import javax.validation.constraints.NotBlank;

public class CardsCommand {
	
	
	@NotBlank(message = "To pole nie może być puste")
	private String deckName;
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
	private int currentCardNumber;
	private int allCardsCount;
	private boolean disablePrevious;
	private boolean disableDelete;
		
	
	public String getDeckName() {
		return deckName;
	}
	public void setDeckName(String deckName) {
		this.deckName = deckName;
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
	
	public int getCurrentCardNumber() {
		return currentCardNumber;
	}
	public void setCurrentCardNumber(int currentCardNumber) {
		this.currentCardNumber = currentCardNumber;
	}
	
	public int getAllCardsCount() {
		return allCardsCount;
	}
	public void setAllCardsCount(int allCardsCount) {
		this.allCardsCount = allCardsCount;
	}
	
	public boolean isDisablePrevious() {
		return disablePrevious;
	}
	public void setDisablePrevious(boolean disablePrevious) {
		this.disablePrevious = disablePrevious;
	}
	
	public boolean isDisableDelete() {
		return disableDelete;
	}
	public void setDisableDelete(boolean disableDelete) {
		this.disableDelete = disableDelete;
	}
	
	
}
