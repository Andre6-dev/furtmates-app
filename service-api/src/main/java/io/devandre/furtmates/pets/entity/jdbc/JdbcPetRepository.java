package io.devandre.furtmates.pets.entity.jdbc;

import io.devandre.furtmates.pets.entity.Pet;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface JdbcPetRepository extends ListCrudRepository<Pet, Long>, PagingAndSortingRepository<Pet, Long> {


    @Modifying
    @Query("UPDATE furtmates_schema.pets SET name = :name, species_id = :speciesId, breed_id = :breedId, age = :age, genre = :genre, weight = :weight, description = :description, size = :size, color = :color, health_status = :healthStatus, characteristics = :characteristics, shelter_id = :shelterId, arrival_date = :arrivalDate, good_with = :goodWith, is_spayed = :isSpayed WHERE id = :id")
    void updatePet(@Param(value = "id") Long id,
                   @Param(value = "name") String name,
                   @Param(value = "speciesId") Integer speciesId,
                   @Param(value = "breedId") Integer breedId,
                   @Param(value = "age") String age,
                   @Param(value = "genre") String genre,
                   @Param(value = "weight") BigDecimal weight,
                   @Param(value = "description") String description,
                   @Param(value = "size") String size,
                   @Param(value = "color") String color,
                   @Param(value = "healthStatus") String healthStatus,
                   @Param(value = "characteristics") String characteristics,
                   @Param(value = "shelterId") Integer shelterId,
                   @Param(value = "arrivalDate") LocalDate arrivalDate,
                   @Param(value = "goodWith") String goodWith,
                   @Param(value = "isSpayed") Boolean isSpayed);

    @Modifying
    @Query("UPDATE furtmates_schema.pets SET adoption_status = 'ADOPTED' WHERE id = :id")
    void adoptPet(@Param(value = "id") Long id);
}
