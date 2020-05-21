package pl.kwi.springboot.enums;

public enum LanguageEnum {
	
	
	POLISH("Język Polski"), ENGLISH("Język Angielski"), RUSSIAN("Język Rosyjski"), SPAIN("Język Hiszpański");
	
	
	private String displayText;

	
	private LanguageEnum(String displayText) {
		this.displayText = displayText;
	}
	
	
	public String getDisplayText() {
		return displayText;
	}
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
	
	
}
