package pl.kwi.springboot.services.intf;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.kwi.springboot.db.entities.DeckEntity;

public interface DeckService {

	DeckEntity save(DeckEntity deck);

	long count();

	Page<DeckEntity> find(Pageable pageable);

	DeckEntity findById(Long id);

	void deleteById(Long id);

}
