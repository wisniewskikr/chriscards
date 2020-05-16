package pl.kwi.springboot.controllers.learning;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kwi.springboot.commands.learning.ConfigureLearningCommand;
import pl.kwi.springboot.enums.LearningModeEnum;

@Controller
@RequestMapping(value="/learning")
public class ConfigureLearningController {

	@RequestMapping(value="/configure")
	public String displayPage(
			@ModelAttribute("command") ConfigureLearningCommand command) {
		
		command.setLearningModes(new ArrayList<LearningModeEnum>(Arrays.asList(LearningModeEnum.values())));
		command.setSelectedLearningMode(LearningModeEnum.MANUAL);
		
		return "learning/configureLearning";
	}
	
	@RequestMapping(value="/run", method = RequestMethod.POST)
	public String run(
			@ModelAttribute("command") ConfigureLearningCommand command) {
		
		System.out.println(command.getDeckCount());
		System.out.println(command.getSelectedLearningMode().getDisplayText());
		
		return "learning/configureLearning";
	}
	
}