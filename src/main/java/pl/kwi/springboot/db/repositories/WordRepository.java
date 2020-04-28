package pl.kwi.springboot.db.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.kwi.springboot.db.entities.WordEntity;

public interface WordRepository extends CrudRepository<WordEntity, Long> {
}
