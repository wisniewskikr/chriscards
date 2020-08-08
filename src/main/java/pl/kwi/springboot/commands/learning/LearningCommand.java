package pl.kwi.springboot.commands.learning;

import pl.kwi.springboot.db.entities.DeckEntity;
import pl.kwi.springboot.enums.LearningModeEnum;

public class LearningCommand {

	
	private Iterable<DeckEntity> decks;
	private LearningModeEnum selectedLearningMode;
	private boolean manualLearningModeRepeat;
	private boolean authomaticLearningModeRepeat;
	private boolean playSpeech;
		
	
	public Iterable<DeckEntity> getDecks() {
		return decks;
	}
	public void setDecks(Iterable<DeckEntity> decks) {
		this.decks = decks;
	}
	
	public LearningModeEnum getSelectedLearningMode() {
		return selectedLearningMode;
	}
	public void setSelectedLearningMode(LearningModeEnum selectedLearningMode) {
		this.selectedLearningMode = selectedLearningMode;
	}
	
	public boolean isManualLearningModeRepeat() {
		return manualLearningModeRepeat;
	}
	public void setManualLearningModeRepeat(boolean manualLearningModeRepeat) {
		this.manualLearningModeRepeat = manualLearningModeRepeat;
	}
	
	public boolean isAuthomaticLearningModeRepeat() {
		return authomaticLearningModeRepeat;
	}
	public void setAuthomaticLearningModeRepeat(boolean authomaticLearningModeRepeat) {
		this.authomaticLearningModeRepeat = authomaticLearningModeRepeat;
	}
	
	public boolean isPlaySpeech() {
		return playSpeech;
	}
	public void setPlaySpeech(boolean playSpeech) {
		this.playSpeech = playSpeech;
	}	
	
	
}
