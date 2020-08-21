package pl.kwi.springboot.commands.more;

import java.util.List;

public class MoreDeleteConfirmationCommand {
	
	
	private List<Long> selectedItems;
	

	public List<Long> getSelectedItems() {
		return selectedItems;
	}
	public void setSelectedItems(List<Long> selectedItems) {
		this.selectedItems = selectedItems;
	}	
		

}
