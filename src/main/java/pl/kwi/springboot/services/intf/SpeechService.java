package pl.kwi.springboot.services.intf;

public interface SpeechService {

	public void createSpeechMp3(String text, String languageCode) throws Exception;

}
