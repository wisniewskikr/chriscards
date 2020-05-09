package pl.kwi.springboot.commands.cards;


public class NewCardCommand {
	
	
	//@NotBlank(message = "To pole nie może być puste")
	private String deckName;
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
	private int currentCardNumber;
	private int allCardsCount;
	private boolean disablePrevious;
	private boolean visibleNext;
		
	
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
	
	public boolean isVisibleNext() {
		return visibleNext;
	}
	public void setVisibleNext(boolean visibleNext) {
		this.visibleNext = visibleNext;
	}
	
	
}
