package pl.kwi.springboot.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORY")
public class CategoryEntity {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
 
    @Column(nullable = false)
    private String name;
		

}
