package pl.kwi.springboot.controllers.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.home.HomeCommand;

@Controller
@RequestMapping(value="/home")
public class HomeController {

	@RequestMapping
	public String cards(
			@ModelAttribute("command") HomeCommand command) {
		return "home/home";
	}
	
}