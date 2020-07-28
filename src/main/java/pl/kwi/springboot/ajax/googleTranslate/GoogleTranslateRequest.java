package pl.kwi.springboot.ajax.googleTranslate;

import java.io.Serializable;

public class GoogleTranslateRequest implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String polishWord;
	private String polishSentence;
	
	
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
	

}
