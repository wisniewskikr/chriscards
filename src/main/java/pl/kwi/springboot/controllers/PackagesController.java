package pl.kwi.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.PackagesCommand;

@Controller
@RequestMapping(value="/packages")
public class PackagesController {

	@RequestMapping
	public String displayPage(
			@ModelAttribute("command") PackagesCommand command) {
		return "packages";
	}
	
}