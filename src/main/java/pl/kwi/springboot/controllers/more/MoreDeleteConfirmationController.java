package pl.kwi.springboot.controllers.more;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kwi.springboot.commands.more.MoreDeleteConfirmationCommand;
import pl.kwi.springboot.pagination.checkboxPagination.controllers.AbstrCheckboxPaginationController;
import pl.kwi.springboot.services.intf.DeckService;

@Controller
@RequestMapping(value="/more/delete/confirmation")
public class MoreDeleteConfirmationController extends AbstrCheckboxPaginationController {
	
	
	@Autowired
	private DeckService deckService;


	@RequestMapping
	public String displayPage(
			@ModelAttribute("command") MoreDeleteConfirmationCommand command) {
		
		List<Long> selectedItems = command.getSelectedItems();
		for (Long id : selectedItems) {
			command.getNames().add(deckService.findById(id).getName());
		}
		
		return "more/moreDeleteConfirmation";
		
	}
	
	@RequestMapping(value="/run", method = RequestMethod.POST)
	public String run(
			@ModelAttribute("command") MoreDeleteConfirmationCommand command) {
		
		List<Long> selectedItems = command.getSelectedItems();
		for (Long long1 : selectedItems) {
			System.out.println(long1);
		}
				
		return "redirect:/home";
		
	}

	
}