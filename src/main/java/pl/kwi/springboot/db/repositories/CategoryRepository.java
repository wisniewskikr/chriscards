package pl.kwi.springboot.db.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pl.kwi.springboot.db.entities.CategoryEntity;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
	
	@Query("SELECT c FROM CategoryEntity c WHERE c.id != 1")
    public Iterable<CategoryEntity> findAllWithoutDefault();
	
}
