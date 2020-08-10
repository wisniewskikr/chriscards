package pl.kwi.springboot.pagination.checkboxPagination.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;

import pl.kwi.springboot.commands.learning.LearningCommand;
import pl.kwi.springboot.db.entities.DeckEntity;

public abstract class AbstrCheckboxPaginationController {
	
	protected void handlePagination(LearningCommand command, Page<DeckEntity> page) {
		
		List<Integer> pages = new ArrayList<Integer>();
		int first = getFirst(command.getCurrentPage(), page.getTotalPages());
		int last = getLast(command.getCurrentPage(), page.getTotalPages());
		for (int i = first; i <= last; i++) {
			pages.add(i);
		}
		command.setPages(pages);
		
		if (command.getCurrentPage() == 1) {
			command.setDisablePrevious(true);
		} else {
			command.setDisablePrevious(false);
		}
		
		if (command.getCurrentPage() == page.getTotalPages()) {
			command.setDisableNext(true);
		} else {
			command.setDisableNext(false);
		}
		
	}
	
	protected void handlePaginationSelectedItems(LearningCommand command) {
		
		if (StringUtils.isBlank(command.getTmpSelectedItems())) {
			return;
		}
		
		List<String> list = Arrays.asList(command.getTmpSelectedItems().split(","));
		command.setSelectedItems(new ArrayList<Long>());
		for (String id : list) {
			command.getSelectedItems().add(Long.valueOf(id));
		}
		
	}
	
	private int getFirst(int currentPage, int totalPages) {
		
		int result = 1;
		
		if (totalPages <= 5) {
			return result;
		}
		
		if ((currentPage - 1 ) > 0) {
			result = currentPage - 1;
		}
		
		if ((currentPage - 2) > 0) {
			result = currentPage - 2;
		}
		
		if ((currentPage - 3) > 0 && (currentPage + 2) > totalPages) {
			result = currentPage - 3;
		}
		
		if ((currentPage - 4) > 0 && (currentPage + 1) > totalPages) {
			result = currentPage - 4;
		}
		
		return result;
		
	}
	
	private int getLast(int currentPage, int totalPages) {
		
		int result = totalPages;
		
		if (totalPages <= 5) {
			return result;
		}
		
		if ((currentPage + 1) <= totalPages) {
			result = currentPage + 1;
		}
		
		if ((currentPage + 2) <= totalPages) {
			result = currentPage + 2;
		}
		
		if ((currentPage + 3 ) < totalPages  && (currentPage - 2) <= 0) {
			result = currentPage + 3;
		}
		
		if ((currentPage + 4) < totalPages  && (currentPage - 1) <= 0) {
			result = currentPage + 4;
		}		
		
		return result;
		
	}
	
	
}
