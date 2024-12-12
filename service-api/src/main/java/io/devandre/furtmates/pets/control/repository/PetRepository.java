package io.devandre.furtmates.pets.control.repository;

import io.devandre.furtmates.pets.boundary.request.CreatePetRequest;
import io.devandre.furtmates.pets.boundary.request.UpdatePetRequest;
import io.devandre.furtmates.pets.boundary.response.PetResponse;
import io.devandre.furtmates.pets.entity.jdbc.PetFilter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PetRepository {

    Page<PetResponse> getPetsWithFilters(PetFilter filter, Pageable pageable);

    PetResponse getPetById(Long id);

    void createPet(CreatePetRequest createPetRequest);

    void deletePet(Long id);

    void updatePet(Long id, UpdatePetRequest updatePetRequest);

    void adoptPet(Long id);
}
