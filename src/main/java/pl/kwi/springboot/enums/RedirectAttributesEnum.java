package pl.kwi.springboot.enums;

public enum RedirectAttributesEnum {
	
	
	CARD_NUMBER("cardNumber"), CARD_COUNT("cardCount"), WORD_NUMBER("wordNumber"), WORD_COUNT("wordCount"), SELECTED_LEARNING_MODE("selectedLearningMode"), 
	MANUAL_REPEAT("manualLearningModeRepeat"), AUTHOMATIC_REPEAT("authomaticLearningModeRepeat"), SKIPPED_CARDS_COUNT("skippedCardsCount"), SELECTED_ITEMS("selectedItems"), SELECTED_ITEM("selectedItem");
	
	private String value;
	

	private RedirectAttributesEnum(String value) {
		this.value = value;
	}
	
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}	

}
