package pl.kwi.springboot.controllers.learning;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/resultLearning")
public class ResultLearningController {

	@RequestMapping(value="/display")
	public String display(
			@ModelAttribute("command") ResultLearningController command,
			HttpSession session) {
		
		return "learning/resultLearning";
		
	}	
	
}