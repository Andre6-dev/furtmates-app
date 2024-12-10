package io.devandre.furtmates.pets.entity.jdbc;

import io.devandre.furtmates.pets.entity.Breed;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JdbcBreedRepository extends ListCrudRepository<Breed, Long> {

    @Query("SELECT * FROM furtmates_schema.breeds WHERE species_id = :speciesId")
    List<Breed> findBySpeciesId(Integer speciesId);
}
