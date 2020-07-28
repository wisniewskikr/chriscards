package pl.kwi.springboot.services.impl;

import org.springframework.stereotype.Service;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.cloud.translate.Translate.TranslateOption;

import pl.kwi.springboot.services.intf.TranslationService;

@Service
public class TranslationServiceImpl implements TranslationService{
	
	@Override
	public String getTranslation(String text, String sourceLanguage, String targetLanguage) {
		
		Translate translate = TranslateOptions.getDefaultInstance().getService();
		Translation translation =
		        translate.translate(
				           text, TranslateOption.sourceLanguage(sourceLanguage), TranslateOption.targetLanguage(targetLanguage));
		return translation.getTranslatedText();
		
	}

}
