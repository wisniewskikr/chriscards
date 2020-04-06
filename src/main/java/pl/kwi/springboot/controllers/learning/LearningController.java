package pl.kwi.springboot.controllers.learning;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.learning.LearningCommand;

@Controller
@RequestMapping(value="/learning")
public class LearningController {

	@RequestMapping
	public String displayPage(
			@ModelAttribute("command") LearningCommand command) {
		return "learning/learning";
	}
	
}