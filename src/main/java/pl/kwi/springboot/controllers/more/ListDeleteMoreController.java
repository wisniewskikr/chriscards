package pl.kwi.springboot.controllers.more;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.more.ListDeleteMoreCommand;

@Controller
@RequestMapping(value="/more/delete/list")
public class ListDeleteMoreController {

	@RequestMapping
	public String displayPage(
			@ModelAttribute("command") ListDeleteMoreCommand command) {
		return "more/listDeleteMore";
	}
	
}