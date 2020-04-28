package pl.kwi.springboot.db.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.kwi.springboot.db.entities.CardEntity;

public interface CardRepository extends CrudRepository<CardEntity, Long> {
}
