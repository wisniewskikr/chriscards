package pl.kwi.springboot.controllers.cards;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.cards.CardsCommand;

@Controller
public class CardsController {

	@RequestMapping(value="/cards")
	public String cards(
			@ModelAttribute("command") CardsCommand command) {
		return "cards/cards";
	}
	
	@RequestMapping(value="/newCard")
	public String newCard(
			@ModelAttribute("command") CardsCommand command) {
		return "cards/newCard";
	}
	
}