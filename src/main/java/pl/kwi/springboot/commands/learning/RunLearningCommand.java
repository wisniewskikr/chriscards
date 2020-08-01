package pl.kwi.springboot.commands.learning;

import pl.kwi.springboot.db.entities.WordEntity;

public class RunLearningCommand {

	
	private int cardNumber;
	private int cardCount;
	private int wordNumber;
	private int wordCount;
	private WordEntity word;
	private boolean firstWord;
	private boolean lastWord;
	private boolean manualLeartingMode;
	private boolean repeat;
	private boolean playSpeech;
	
	
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public int getCardCount() {
		return cardCount;
	}
	public void setCardCount(int cardCount) {
		this.cardCount = cardCount;
	}
	
	public int getWordNumber() {
		return wordNumber;
	}
	public void setWordNumber(int wordNumber) {
		this.wordNumber = wordNumber;
	}
	
	public int getWordCount() {
		return wordCount;
	}
	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}
	
	public WordEntity getWord() {
		return word;
	}
	public void setWord(WordEntity word) {
		this.word = word;
	}
	
	public boolean isFirstWord() {
		return firstWord;
	}
	public void setFirstWord(boolean firstWord) {
		this.firstWord = firstWord;
	}
	
	public boolean isLastWord() {
		return lastWord;
	}
	public void setLastWord(boolean lastWord) {
		this.lastWord = lastWord;
	}
	
	public boolean isManualLeartingMode() {
		return manualLeartingMode;
	}
	public void setManualLeartingMode(boolean manualLeartingMode) {
		this.manualLeartingMode = manualLeartingMode;
	}
	
	public boolean isRepeat() {
		return repeat;
	}
	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}
	
	public boolean isPlaySpeech() {
		return playSpeech;
	}
	public void setPlaySpeech(boolean playSpeech) {
		this.playSpeech = playSpeech;
	}
	
	
}
