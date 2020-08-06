package pl.kwi.springboot.db.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="DECK")
public class DeckEntity {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
 
    @Column(nullable = false, unique = true)
    private String name;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationTimestamp;

    @OneToMany(
            mappedBy = "deck",
            cascade = CascadeType.ALL,
            orphanRemoval = true
        )
    private List<CardEntity> cards = new ArrayList<CardEntity>();
    
    
    public DeckEntity() {
	}
	public DeckEntity(String name) {
		this.name = name;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getModificationTimestamp() {
		return modificationTimestamp;
	}
	public void setModificationTimestamp(Date modificationTimestamp) {
		this.modificationTimestamp = modificationTimestamp;
	}
	
	public List<CardEntity> getCards() {
		return cards;
	}
	public void setCards(List<CardEntity> cards) {
		this.cards = cards;
	}
	

}
