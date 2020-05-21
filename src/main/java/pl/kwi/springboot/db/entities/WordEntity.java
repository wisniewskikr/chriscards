package pl.kwi.springboot.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pl.kwi.springboot.enums.LanguageEnum;

@Entity
@Table(name="WORD")
public class WordEntity {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
 
    @Column(nullable = false)
    private String word;
    
    @Column
    private String wordPrononciation;
    
    @Column(nullable = false)
    private String sentence;
    
    @Column
    private String sentencePrononciation;
    
    @Enumerated(EnumType.STRING)
    private LanguageEnum language;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private CardEntity card;

    
    public WordEntity() {
	}
	public WordEntity(String word, String wordPrononciation, String sentence, String sentencePrononciation,
			LanguageEnum language) {
		this.word = word;
		this.wordPrononciation = wordPrononciation;
		this.sentence = sentence;
		this.sentencePrononciation = sentencePrononciation;
		this.language = language;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}

	public String getWordPrononciation() {
		return wordPrononciation;
	}
	public void setWordPrononciation(String wordPrononciation) {
		this.wordPrononciation = wordPrononciation;
	}

	public String getSentence() {
		return sentence;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}		

	public String getSentencePrononciation() {
		return sentencePrononciation;
	}
	public void setSentencePrononciation(String sentencePrononciation) {
		this.sentencePrononciation = sentencePrononciation;
	}
	
	public LanguageEnum getLanguage() {
		return language;
	}
	public void setLanguage(LanguageEnum language) {
		this.language = language;
	}
	
	public CardEntity getCard() {
		return card;
	}
	public void setCard(CardEntity card) {
		this.card = card;
	}	
		

}
