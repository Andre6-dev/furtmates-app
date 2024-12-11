package io.devandre.furtmates.pets.entity.jdbc;

import io.devandre.furtmates.pets.entity.Pet;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class PetRowMapper implements RowMapper<Pet> {

    @Override
    public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pet pet = new Pet();
        pet.setId(rs.getLong("id"));
        pet.setName(rs.getString("name"));
        pet.setSpeciesId(rs.getInt("species_id"));
        pet.setBreedId(rs.getInt("breed_id"));
        pet.setAge(rs.getString("age"));
        pet.setGenre(rs.getString("genre"));
        pet.setWeight(rs.getBigDecimal("weight"));
        pet.setDescription(rs.getString("description"));
        pet.setSize(rs.getString("size"));
        pet.setColor(rs.getString("color"));
        pet.setHealthStatus(rs.getString("health_status"));
        pet.setCharacteristics(rs.getString("characteristics"));
        pet.setAdoptionStatus(rs.getString("adoption_status"));
        pet.setShelterId(rs.getInt("shelter_id"));
        pet.setArrivalDate(rs.getObject("arrival_date", LocalDate.class));
        pet.setGoodWith(rs.getString("good_with"));
        pet.setIsSpayed(rs.getBoolean("is_spayed"));
        pet.setUpdatedBy(rs.getInt("updated_by"));
        return pet;
    }
}
