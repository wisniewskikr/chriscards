package pl.kwi.springboot.controllers.packages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.packages.PackagesCommand;

@Controller
@RequestMapping(value="/packages")
public class PackagesController {

	@RequestMapping
	public String displayPage(
			@ModelAttribute("command") PackagesCommand command) {
		return "packages/packages";
	}
	
}