package pl.kwi.springboot.db.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ARTICLE")
public class ArticleEntity {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
 
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private Date date;
    
    @Column(nullable = false)
    private String pair;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dance_type_id")
    private DanceTypeEntity danceType;
    
    @Column(nullable = false, name = "YOUTUBE")
    private String youTube;
    
    @Column(nullable = false)
    private String thumb;
    
    
	public ArticleEntity() {
	}
	
	public ArticleEntity(String title) {
		this.title = title;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}	

	public String getPair() {
		return pair;
	}
	public void setPair(String pair) {
		this.pair = pair;
	}

	public DanceTypeEntity getDanceType() {
		return danceType;
	}
	public void setDanceType(DanceTypeEntity danceType) {
		this.danceType = danceType;
	}

	public String getYouTube() {
		return youTube;
	}
	public void setYouTube(String youTube) {
		this.youTube = youTube;
	}

	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}	
	

}
