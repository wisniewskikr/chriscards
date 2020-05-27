package pl.kwi.springboot.commands.learning;

import pl.kwi.springboot.enums.LearningModeEnum;

public class ConfigureLearningCommand {

	
	private int deckCount;
	private LearningModeEnum selectedLearningMode;
	
	
	public int getDeckCount() {
		return deckCount;
	}
	public void setDeckCount(int deckCount) {
		this.deckCount = deckCount;
	}
	
	public LearningModeEnum getSelectedLearningMode() {
		return selectedLearningMode;
	}
	public void setSelectedLearningMode(LearningModeEnum selectedLearningMode) {
		this.selectedLearningMode = selectedLearningMode;
	}
		

}
