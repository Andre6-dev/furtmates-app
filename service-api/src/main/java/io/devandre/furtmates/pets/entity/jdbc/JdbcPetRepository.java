package io.devandre.furtmates.pets.entity.jdbc;

import io.devandre.furtmates.pets.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcPetRepository extends ListCrudRepository<Pet, Long>, PagingAndSortingRepository<Pet, Long> {

}
