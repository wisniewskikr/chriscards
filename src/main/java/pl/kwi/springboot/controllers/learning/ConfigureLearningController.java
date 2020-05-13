package pl.kwi.springboot.controllers.learning;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.learning.ConfigureLearningCommand;

@Controller
@RequestMapping(value="/learning/configure")
public class ConfigureLearningController {

	@RequestMapping
	public String displayPage(
			@ModelAttribute("command") ConfigureLearningCommand command) {
		return "learning/configureLearning";
	}
	
}