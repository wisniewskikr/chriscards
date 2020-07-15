package pl.kwi.springboot.googleTranslate;

import java.io.Serializable;

public class GoogleTranslateResponse implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String status;
	private String message;
	private String englishWord;
	private String englishSentence;
	
	
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
	

}
