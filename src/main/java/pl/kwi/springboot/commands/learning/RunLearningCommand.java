package pl.kwi.springboot.commands.learning;

import pl.kwi.springboot.db.entities.WordEntity;
import pl.kwi.springboot.enums.LearningModeEnum;

public class RunLearningCommand {

	
	private int cardNumber;
	private int cardCount;
	private int wordNumber;
	private int wordCount;
	private LearningModeEnum selectedLearningMode;
	private WordEntity word;
	
	
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
	
	public LearningModeEnum getSelectedLearningMode() {
		return selectedLearningMode;
	}
	public void setSelectedLearningMode(LearningModeEnum selectedLearningMode) {
		this.selectedLearningMode = selectedLearningMode;
	}
	
	public WordEntity getWord() {
		return word;
	}
	public void setWord(WordEntity word) {
		this.word = word;
	}
		

}
