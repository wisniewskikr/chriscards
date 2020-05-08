package pl.kwi.springboot.db.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.kwi.springboot.db.entities.DeckEntity;

public interface DeckRepository extends CrudRepository<DeckEntity, Long> {
}
