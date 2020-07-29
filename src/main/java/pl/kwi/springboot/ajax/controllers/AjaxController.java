package pl.kwi.springboot.ajax.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.kwi.springboot.ajax.googlePlay.GooglePlayRequest;
import pl.kwi.springboot.ajax.googlePlay.GooglePlayResponse;
import pl.kwi.springboot.ajax.googleTranslate.GoogleTranslateRequest;
import pl.kwi.springboot.ajax.googleTranslate.GoogleTranslateResponse;
import pl.kwi.springboot.services.intf.Mp3Service;
import pl.kwi.springboot.services.intf.SpeechService;
import pl.kwi.springboot.services.intf.TranslationService;

@Controller
@RequestMapping(value="/ajax")
public class AjaxController {
	
	
	private static final String SPAIN_LANGUAGE_CODE = "es";
	private static final String RUSSIAN_LANGUAGE_CODE = "ru";
	private static final String ENGLISH_LANGUAGE_CODE = "en";
	private static final String POLISH_LANGUAGE_CODE = "pl";
	
	@Autowired
	private TranslationService translationService;	
	
	@Autowired
	private Mp3Service mp3Service;
	
	@Autowired
	private SpeechService speechService;
	
	
	@RequestMapping(value="/translate", method=RequestMethod.POST)
	public @ResponseBody GoogleTranslateResponse translateAjax(@Valid @RequestBody GoogleTranslateRequest request, BindingResult result) {
		
		GoogleTranslateResponse response = new GoogleTranslateResponse();
		
		if(result.hasErrors()) {
			response.setStatus("FAIL");
			response.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return response;
		}		
		
		response.setStatus("SUCCESS");		
		response.setEnglishWord(translationService.getTranslation(request.getPolishWord(), POLISH_LANGUAGE_CODE, ENGLISH_LANGUAGE_CODE));
		response.setEnglishSentence(translationService.getTranslation(request.getPolishSentence(), POLISH_LANGUAGE_CODE, ENGLISH_LANGUAGE_CODE));
		response.setRussianWord(translationService.getTranslation(request.getPolishWord(), POLISH_LANGUAGE_CODE, RUSSIAN_LANGUAGE_CODE));
		response.setRussianSentence(translationService.getTranslation(request.getPolishSentence(), POLISH_LANGUAGE_CODE, RUSSIAN_LANGUAGE_CODE));
		response.setSpainWord(translationService.getTranslation(request.getPolishWord(), POLISH_LANGUAGE_CODE, SPAIN_LANGUAGE_CODE));
		response.setSpainSentence(translationService.getTranslation(request.getPolishSentence(), POLISH_LANGUAGE_CODE, SPAIN_LANGUAGE_CODE));
		return response;
		
	}	
	
	@RequestMapping(value="/play", method=RequestMethod.POST)
	public @ResponseBody GooglePlayResponse playAjax(@Valid @RequestBody GooglePlayRequest request, BindingResult result) throws Exception {
		
		GooglePlayResponse response = new GooglePlayResponse();
		
		if(result.hasErrors()) {
			response.setStatus("FAIL");
			response.setMessage(result.getAllErrors().get(0).getDefaultMessage());
			return response;
		}		
		
		speechService.createSpeechMp3(request.getText(), request.getLanguageCode());
		mp3Service.play("tmp.mp3");
		
		return response;
		
	}

}
