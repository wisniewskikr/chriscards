package pl.kwi.springboot.pagination.radioPagination.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;

import pl.kwi.springboot.pagination.radioPagination.commands.AbstrRadioPagiantionCommand;

public abstract class AbstrRadioPaginationController {
	
	
	private static final int PAGES_IN_PAGINATION = 5;
	

	protected void handlePagination(AbstrRadioPagiantionCommand command, Page<?> page) {
		
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
		
		if (command.getCurrentPage() == page.getTotalPages() || pages.isEmpty()) {
			command.setDisableNext(true);
		} else {
			command.setDisableNext(false);
		}
		
	}
	
	protected void handlePaginationSelectedItems(AbstrRadioPagiantionCommand command) {
		
		if (StringUtils.isBlank(command.getTmpSelectedItem())) {
			return;
		}
		
		command.setSelectedItem(Long.valueOf(command.getTmpSelectedItem()));
		
	}
	
	private int getFirst(int currentPage, int totalPages) {
		
		int result = 1;
		
		if (totalPages <= PAGES_IN_PAGINATION) {
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
		
		if (totalPages <= PAGES_IN_PAGINATION) {
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
