package pl.kwi.springboot.db.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CARD")
public class CardEntity {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
	private CategoryEntity category;
	
	@OneToMany(
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	 )
	@JoinColumn(name = "card_id")
	private List<WordEntity> words = new ArrayList<WordEntity>();
 

	public CardEntity() {
	}
	public CardEntity(CategoryEntity category, List<WordEntity> words) {
		this.category = category;
		this.words = words;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public CategoryEntity getCategory() {
		return category;
	}
	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public List<WordEntity> getWords() {
		return words;
	}
	public void setWords(List<WordEntity> words) {
		this.words = words;
	}


}
