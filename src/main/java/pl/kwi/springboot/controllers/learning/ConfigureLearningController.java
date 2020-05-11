package pl.kwi.springboot.controllers.learning;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/learning/configure")
public class ConfigureLearningController {

	@RequestMapping
	public String displayPage(
			@ModelAttribute("command") ConfigureLearningController command) {
		return "learning/configureLearning";
	}
	
}