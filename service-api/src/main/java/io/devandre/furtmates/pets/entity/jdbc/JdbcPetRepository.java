package io.devandre.furtmates.pets.entity.jdbc;

import io.devandre.furtmates.pets.entity.Pet;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcPetRepository extends ListCrudRepository<Pet, Long> {
}
