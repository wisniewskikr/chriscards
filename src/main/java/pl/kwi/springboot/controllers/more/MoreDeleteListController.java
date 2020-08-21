package pl.kwi.springboot.controllers.more;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.kwi.springboot.commands.more.MoreDeleteListCommand;
import pl.kwi.springboot.db.entities.DeckEntity;
import pl.kwi.springboot.enums.RedirectAttributesEnum;
import pl.kwi.springboot.pagination.checkboxPagination.controllers.AbstrCheckboxPaginationController;
import pl.kwi.springboot.services.intf.DeckService;

@Controller
@RequestMapping(value="/more/delete/list")
public class MoreDeleteListController extends AbstrCheckboxPaginationController {
	
	@Autowired
	private DeckService deckService;
	
	@Value("${cards.count.learning}")
    private int cartsCount;

	@RequestMapping
	public String displayPage(
			@ModelAttribute("command") MoreDeleteListCommand command) {
		
		Page<DeckEntity> deckPage = deckService.find(PageRequest.of(command.getCurrentPage() - 1, cartsCount, Sort.by(Sort.Direction.DESC, "modificationTimestamp")));
		command.setDecks(deckPage.getContent());
		handlePaginationSelectedItems(command);
		handlePagination(command, deckPage);
		
		return "more/moreDeleteList";
		
	}
	
	@RequestMapping(value="/run", method = RequestMethod.POST)
	public String run(
			@ModelAttribute("command") MoreDeleteListCommand command,
			RedirectAttributes attributes) {
		
		handlePaginationSelectedItems(command);	
		attributes.addAttribute(RedirectAttributesEnum.SELECTED_ITEMS.getValue(), command.getSelectedItems());		
		
		return "redirect:/more/delete/confirmation";
		
	}
	
}