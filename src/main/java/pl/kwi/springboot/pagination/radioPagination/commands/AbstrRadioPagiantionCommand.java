package pl.kwi.springboot.pagination.radioPagination.commands;

import java.util.List;

public abstract class AbstrRadioPagiantionCommand {
	
	
	private List<Integer> pages;
	private int currentPage = 1;
	private boolean disablePrevious;
	private boolean disableNext;
	private String tmpSelectedItem;
	private Long selectedItem;
	
	
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
	
	public String getTmpSelectedItem() {
		return tmpSelectedItem;
	}
	public void setTmpSelectedItem(String tmpSelectedItem) {
		this.tmpSelectedItem = tmpSelectedItem;
	}
	
	public Long getSelectedItem() {
		return selectedItem;
	}
	public void setSelectedItem(Long selectedItem) {
		this.selectedItem = selectedItem;
	}	
	
	
}
