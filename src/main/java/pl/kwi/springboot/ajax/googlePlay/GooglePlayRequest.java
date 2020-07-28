package pl.kwi.springboot.ajax.googlePlay;

import java.io.Serializable;

public class GooglePlayRequest implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String text;

	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
		

}
