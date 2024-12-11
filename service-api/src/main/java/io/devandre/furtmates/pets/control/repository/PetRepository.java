package io.devandre.furtmates.pets.control.repository;

import io.devandre.furtmates.pets.boundary.response.PetResponse;
import io.devandre.furtmates.pets.entity.jdbc.PetFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PetRepository {

    Page<PetResponse> getPetsWithFilters(PetFilter filter, Pageable pageable);
}
