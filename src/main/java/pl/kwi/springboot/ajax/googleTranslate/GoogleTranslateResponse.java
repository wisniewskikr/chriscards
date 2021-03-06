package pl.kwi.springboot.ajax.googleTranslate;

import java.io.Serializable;

public class GoogleTranslateResponse implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String status;
	private String message;
	private String englishWord;
	private String englishSentence;
	private String russianWord;
	private String russianSentence;
	private String spainWord;
	private String spainSentence;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	

}
