package io.devandre.furtmates.pets.entity.jdbc;

import io.devandre.furtmates.pets.entity.PetImage;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcPetImageRepository extends ListCrudRepository<PetImage, Long> {
}
