package pl.kwi.springboot.db.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import pl.kwi.springboot.db.entities.DeckEntity;

public interface DeckRepository extends PagingAndSortingRepository<DeckEntity, Long> {
	
	@Query("SELECT max(d.id) FROM DeckEntity d")
	public Long getMaxId();
	
	@Query("SELECT d FROM DeckEntity d")
    public Page<DeckEntity> findLastDecks(Pageable pageable);
	
}
