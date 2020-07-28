package pl.kwi.springboot.ajax.googlePlay;

import java.io.Serializable;

public class GooglePlayResponse implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String status;
	private String message;	
	
	
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
		

}
