package pl.kwi.springboot.commands.learning;

import pl.kwi.springboot.enums.LearningModeEnum;
import pl.kwi.springboot.enums.LearningTypeEnum;

public class ConfigureLearningCommand {

	
	private int deckCount;
	private LearningTypeEnum learningType;
	private LearningModeEnum learningMode;
	
	
	public int getDeckCount() {
		return deckCount;
	}
	public void setDeckCount(int deckCount) {
		this.deckCount = deckCount;
	}
	
	public LearningTypeEnum getLearningType() {
		return learningType;
	}
	public void setLearningType(LearningTypeEnum learningType) {
		this.learningType = learningType;
	}
	
	public LearningModeEnum getLearningMode() {
		return learningMode;
	}
	public void setLearningMode(LearningModeEnum learningMode) {
		this.learningMode = learningMode;
	}
	

}
