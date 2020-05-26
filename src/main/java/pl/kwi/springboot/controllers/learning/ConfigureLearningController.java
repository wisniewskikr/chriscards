package pl.kwi.springboot.controllers.learning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.kwi.springboot.commands.learning.ConfigureLearningCommand;
import pl.kwi.springboot.db.entities.CardEntity;
import pl.kwi.springboot.db.entities.DeckEntity;
import pl.kwi.springboot.db.repositories.DeckRepository;
import pl.kwi.springboot.enums.LearningModeEnum;

@Controller
@RequestMapping(value="/configureLearning")
public class ConfigureLearningController {
	
	@Autowired
	private DeckRepository deckRepository;

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
			RedirectAttributes attributes,
			HttpSession session) {
		
		List<CardEntity> cards = new ArrayList<CardEntity>();
		List<DeckEntity> decks = deckRepository.findLastDecks(PageRequest.of(0,command.getDeckCount(),Sort.by(Sort.Direction.DESC, "id"))).getContent();
		for (DeckEntity deck : decks) {
			cards.addAll(deck.getCards());
		}
		session.setAttribute("cards", cards);
		session.setAttribute("notValidCards", new ArrayList<CardEntity>());
		
		attributes.addAttribute("cardNumber", 1);
		attributes.addAttribute("cardCount", cards.size());
		attributes.addAttribute("wordNumber", 1);
		attributes.addAttribute("wordCount", cards.get(0).getWords().size());
		attributes.addAttribute("selectedLearningMode", command.getSelectedLearningMode());
		
		return "redirect:/runLearning/run";
	}
	
}