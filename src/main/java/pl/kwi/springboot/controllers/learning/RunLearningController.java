package pl.kwi.springboot.controllers.learning;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.learning.RunLearningCommand;

@Controller
@RequestMapping(value="/runLearning")
public class RunLearningController {

	@RequestMapping(value="/run")
	public String displayPage(
			@ModelAttribute("command") RunLearningCommand command) {
		
		System.out.println(command.getDeckCount());
		System.out.println(command.getSelectedLearningMode().getDisplayText());
		
		return "learning/runLearning";
	}
	
}