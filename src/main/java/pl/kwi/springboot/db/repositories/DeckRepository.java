package pl.kwi.springboot.db.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pl.kwi.springboot.db.entities.DeckEntity;

public interface DeckRepository extends CrudRepository<DeckEntity, Long> {
	
	@Query("SELECT max(d.id) FROM DeckEntity d")
	public Long getMaxId();
	
}
