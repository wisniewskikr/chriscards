package pl.kwi.springboot.controllers.learning;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.learning.ConfigureLearningCommand;
import pl.kwi.springboot.enums.LearningModeEnum;

@Controller
@RequestMapping(value="/learning/configure")
public class ConfigureLearningController {

	@RequestMapping
	public String displayPage(
			@ModelAttribute("command") ConfigureLearningCommand command) {
		
		command.setLearningModes(new ArrayList<LearningModeEnum>(Arrays.asList(LearningModeEnum.values())));
		command.setSelectedLearningMode(LearningModeEnum.MANUAL);
		
		return "learning/configureLearning";
	}
	
}