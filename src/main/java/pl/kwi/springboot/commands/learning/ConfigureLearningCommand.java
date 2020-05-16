package pl.kwi.springboot.commands.learning;

import java.util.ArrayList;
import java.util.List;

import pl.kwi.springboot.enums.LearningModeEnum;

public class ConfigureLearningCommand {

	
	private int deckCount;
	private List<LearningModeEnum> learningModes = new ArrayList<LearningModeEnum>();
	private LearningModeEnum selectedLearningMode;
	
	
	public int getDeckCount() {
		return deckCount;
	}
	public void setDeckCount(int deckCount) {
		this.deckCount = deckCount;
	}
	
	public List<LearningModeEnum> getLearningModes() {
		return learningModes;
	}
	public void setLearningModes(List<LearningModeEnum> learningModes) {
		this.learningModes = learningModes;
	}
	
	public LearningModeEnum getSelectedLearningMode() {
		return selectedLearningMode;
	}
	public void setSelectedLearningMode(LearningModeEnum selectedLearningMode) {
		this.selectedLearningMode = selectedLearningMode;
	}
		

}
