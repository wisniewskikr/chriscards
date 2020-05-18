package pl.kwi.springboot.controllers.learning;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.kwi.springboot.commands.learning.ConfigureLearningCommand;
import pl.kwi.springboot.enums.LearningModeEnum;

@Controller
@RequestMapping(value="/configureLearning")
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
			@ModelAttribute("command") ConfigureLearningCommand command,
			RedirectAttributes attributes) {
		
		attributes.addAttribute("deckCount", command.getDeckCount());
		attributes.addAttribute("selectedLearningMode", command.getSelectedLearningMode());
		
		return "redirect:/runLearning/run";
	}
	
}