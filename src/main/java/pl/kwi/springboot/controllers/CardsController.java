package pl.kwi.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.CardsCommand;

@Controller
@RequestMapping(value="/cards")
public class CardsController {

	@RequestMapping
	public String displayPage(
			@ModelAttribute("command") CardsCommand command) {
		return "cards";
	}
	
}