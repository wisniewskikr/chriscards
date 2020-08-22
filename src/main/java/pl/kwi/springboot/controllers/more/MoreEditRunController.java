package pl.kwi.springboot.controllers.more;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kwi.springboot.commands.more.MoreEditRunCommand;
import pl.kwi.springboot.pagination.checkboxPagination.controllers.AbstrCheckboxPaginationController;
import pl.kwi.springboot.services.intf.DeckService;

@Controller
@RequestMapping(value="/more/edit/run")
public class MoreEditRunController extends AbstrCheckboxPaginationController {
	
	
	@Autowired
	private DeckService deckService;


	@RequestMapping
	public String displayPage(
			@ModelAttribute("command") MoreEditRunCommand command) {
		
		return "more/moreEditRun";
		
	}

	
}