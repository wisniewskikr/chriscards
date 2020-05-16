package pl.kwi.springboot.enums;

public enum LearningModeEnum {
	
	MANUAL("Manualny Tryb Nauki"), AUTHOMATIC("Automatyczny Tryb Nauki");
	
	private String displayText;
	
		
	private LearningModeEnum(String displayText) {
		this.displayText = displayText;
	}
	
	
	public String getDisplayText() {
		return displayText;
	}
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
	
	

}
