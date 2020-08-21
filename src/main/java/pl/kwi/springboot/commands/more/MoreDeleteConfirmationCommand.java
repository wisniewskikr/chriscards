package pl.kwi.springboot.commands.more;

import java.util.ArrayList;
import java.util.List;

public class MoreDeleteConfirmationCommand {
	
	
	private List<Long> selectedItems = new ArrayList<Long>();
	private List<String> names = new ArrayList<String>();
	

	public List<Long> getSelectedItems() {
		return selectedItems;
	}
	public void setSelectedItems(List<Long> selectedItems) {
		this.selectedItems = selectedItems;
	}
	
	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}
			

}
