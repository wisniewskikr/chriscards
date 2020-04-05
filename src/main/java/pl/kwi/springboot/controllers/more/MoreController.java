package pl.kwi.springboot.controllers.more;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.more.MoreCommand;

@Controller
@RequestMapping(value="/more")
public class MoreController {

	@RequestMapping
	public String displayPage(
			@ModelAttribute("command") MoreCommand command) {
		return "more/more";
	}
	
}