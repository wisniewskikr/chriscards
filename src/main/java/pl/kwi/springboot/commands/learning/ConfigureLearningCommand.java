package pl.kwi.springboot.commands.learning;

import pl.kwi.springboot.enums.LearningModeEnum;

public class ConfigureLearningCommand {

	
	private int deckCount;
	private LearningModeEnum learningMode;
	
	
	public int getDeckCount() {
		return deckCount;
	}
	public void setDeckCount(int deckCount) {
		this.deckCount = deckCount;
	}
	
	public LearningModeEnum getLearningMode() {
		return learningMode;
	}
	public void setLearningMode(LearningModeEnum learningMode) {
		this.learningMode = learningMode;
	}
	

}
