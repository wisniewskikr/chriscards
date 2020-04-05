package pl.kwi.springboot.controllers.cards;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.cards.NewCardCommand;

@Controller
public class NewCardController {

	@RequestMapping(value="/newCard")
	public String newCard(
			@ModelAttribute("command") NewCardCommand command) {
		return "cards/newCard";
	}
	
}