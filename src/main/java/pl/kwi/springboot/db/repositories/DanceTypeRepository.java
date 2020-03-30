package pl.kwi.springboot.db.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.kwi.springboot.db.entities.DanceTypeEntity;

public interface DanceTypeRepository extends CrudRepository<DanceTypeEntity, Long> {
	
}
