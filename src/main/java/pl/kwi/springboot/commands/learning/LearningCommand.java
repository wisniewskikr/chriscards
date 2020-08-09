package pl.kwi.springboot.commands.learning;

import java.util.ArrayList;
import java.util.List;

import pl.kwi.springboot.db.entities.DeckEntity;
import pl.kwi.springboot.enums.LearningModeEnum;

public class LearningCommand {

	
	private Iterable<DeckEntity> decks;
	private LearningModeEnum selectedLearningMode;
	private boolean manualLearningModeRepeat;
	private boolean authomaticLearningModeRepeat;
	private boolean playSpeech;
	// Pagination
	private List<Integer> pages;
	private int currentPage = 1;
	private boolean disablePrevious;
	private boolean disableNext;
	private List<Long> selectedItems  = new ArrayList<Long>();
		
	
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
	
	public List<Integer> getPages() {
		return pages;
	}
	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public boolean isDisablePrevious() {
		return disablePrevious;
	}
	public void setDisablePrevious(boolean disablePrevious) {
		this.disablePrevious = disablePrevious;
	}
	
	public boolean isDisableNext() {
		return disableNext;
	}
	public void setDisableNext(boolean disableNext) {
		this.disableNext = disableNext;
	}
	
	public List<Long> getSelectedItems() {
		return selectedItems;
	}
	public void setSelectedItems(List<Long> selectedItems) {
		this.selectedItems = selectedItems;
	}	
	
		
}
