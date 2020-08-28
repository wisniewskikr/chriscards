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
    
    @Column(nullable = false)
    private String sentence;
    
    @Enumerated(EnumType.STRING)
    private LanguageEnum language;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private CardEntity card;

    
    public WordEntity() {
	}
	public WordEntity(String word, String sentence, LanguageEnum language) {
		this.word = word;
		this.sentence = sentence;
		this.language = language;
	}
	public WordEntity(long id, String word, String sentence, LanguageEnum language) {
		this.id = id;
		this.word = word;
		this.sentence = sentence;
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

	public String getSentence() {
		return sentence;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
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
