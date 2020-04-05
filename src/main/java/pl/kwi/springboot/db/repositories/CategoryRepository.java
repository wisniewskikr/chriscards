package pl.kwi.springboot.db.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.kwi.springboot.db.entities.CategoryEntity;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
	
}
