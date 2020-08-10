package pl.kwi.springboot.pagination.checkboxPagination.commands;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstrCheckboxPagiantionCommand {
	
	
	private List<Integer> pages;
	private int currentPage = 1;
	private boolean disablePrevious;
	private boolean disableNext;
	private String tmpSelectedItems;
	private List<Long> selectedItems  = new ArrayList<Long>();
	
	
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
	
	public String getTmpSelectedItems() {
		return tmpSelectedItems;
	}
	public void setTmpSelectedItems(String tmpSelectedItems) {
		this.tmpSelectedItems = tmpSelectedItems;
	}
	
	public List<Long> getSelectedItems() {
		return selectedItems;
	}
	public void setSelectedItems(List<Long> selectedItems) {
		this.selectedItems = selectedItems;
	}

}
