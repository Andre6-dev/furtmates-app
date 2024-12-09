package io.devandre.furtmates.pets.entity.jdbc;

import io.devandre.furtmates.pets.entity.Shelter;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcShelterRepository extends ListCrudRepository<Shelter, Long> {
}
