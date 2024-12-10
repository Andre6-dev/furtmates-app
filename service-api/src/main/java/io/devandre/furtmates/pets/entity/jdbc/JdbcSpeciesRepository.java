package io.devandre.furtmates.pets.entity.jdbc;

import io.devandre.furtmates.pets.entity.Specie;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcSpeciesRepository extends ListCrudRepository<Specie, Long> {
}
