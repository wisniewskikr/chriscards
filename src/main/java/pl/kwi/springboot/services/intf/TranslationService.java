package pl.kwi.springboot.services.intf;

public interface TranslationService {

	public String getTranslation(String text, String sourceLanguage, String targetLanguage);

}
