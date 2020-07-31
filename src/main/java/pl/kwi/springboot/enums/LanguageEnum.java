package pl.kwi.springboot.enums;

public enum LanguageEnum {
	
	
	POLISH("Język Polski", "pl"), ENGLISH("Język Angielski", "en-US"), RUSSIAN("Język Rosyjski", "ru"), SPAIN("Język Hiszpański", "es");
	
	
	private String displayText;
	private String languageCode;

	
	private LanguageEnum(String displayText, String languageCode) {
		this.displayText = displayText;
		this.languageCode = languageCode;
	}
	
	
	public String getDisplayText() {
		return displayText;
	}
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}


	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	
	
}
