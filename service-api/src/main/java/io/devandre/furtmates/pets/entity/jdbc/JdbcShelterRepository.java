package io.devandre.furtmates.pets.entity.jdbc;

import io.devandre.furtmates.pets.entity.Shelter;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcShelterRepository extends ListCrudRepository<Shelter, Long> {

    @Modifying
    @Query("UPDATE furtmates_schema.shelters " +
            "SET name = :name, phone_number = :phoneNumber, address = :address, email = :email " +
            "WHERE id = :id")
    void updateShelter(@Param("id") Long id,
                       @Param("name") String name,
                       @Param("phoneNumber") String phoneNumber,
                       @Param("address") String address,
                       @Param("email") String email);


}
