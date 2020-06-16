package pl.kwi.springboot.commands.learning;

public class ResultLearningCommand {

	
	private int cardsCount;
	private int validCardsCount;
	private int notValidCardsCount;
	private int skippedCardsCount;
	private boolean manualLeartingMode;
	private boolean repeat;
	
	
	public int getCardsCount() {
		return cardsCount;
	}
	public void setCardsCount(int cardsCount) {
		this.cardsCount = cardsCount;
	}
	
	public int getValidCardsCount() {
		return validCardsCount;
	}
	public void setValidCardsCount(int validCardsCount) {
		this.validCardsCount = validCardsCount;
	}
	
	public int getNotValidCardsCount() {
		return notValidCardsCount;
	}
	public void setNotValidCardsCount(int notValidCardsCount) {
		this.notValidCardsCount = notValidCardsCount;
	}
	
	public int getSkippedCardsCount() {
		return skippedCardsCount;
	}
	public void setSkippedCardsCount(int skippedCardsCount) {
		this.skippedCardsCount = skippedCardsCount;
	}
	
	public boolean isManualLeartingMode() {
		return manualLeartingMode;
	}
	public void setManualLeartingMode(boolean manualLeartingMode) {
		this.manualLeartingMode = manualLeartingMode;
	}
	
	public boolean isRepeat() {
		return repeat;
	}
	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}	
	
	
}
