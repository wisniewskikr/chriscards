package pl.kwi.springboot.db.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="DANCE_TYPE")
public class DanceTypeEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
 
    @Column(nullable = false)
    private String name;
    
    @OneToMany(
        mappedBy = "danceType",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ArticleEntity> articles = new ArrayList<ArticleEntity>();
    
    
	public DanceTypeEntity() {
	}
	
	public DanceTypeEntity(String name) {
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

	public List<ArticleEntity> getArticles() {
		return articles;
	}
	public void setArticles(List<ArticleEntity> articles) {
		this.articles = articles;
	} 		

}
