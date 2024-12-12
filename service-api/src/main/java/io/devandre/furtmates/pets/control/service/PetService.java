package io.devandre.furtmates.pets.control.service;

import io.devandre.furtmates.pets.boundary.request.CreatePetRequest;
import io.devandre.furtmates.pets.boundary.request.CreateShelterRequest;
import io.devandre.furtmates.pets.boundary.request.UpdatePetRequest;
import io.devandre.furtmates.pets.boundary.request.UpdateShelterRequest;
import io.devandre.furtmates.pets.boundary.response.BreedResponse;
import io.devandre.furtmates.pets.boundary.response.PetResponse;
import io.devandre.furtmates.pets.boundary.response.ShelterResponse;
import io.devandre.furtmates.pets.boundary.response.SpecieResponse;
import io.devandre.furtmates.pets.control.repository.BreedRepository;
import io.devandre.furtmates.pets.control.repository.PetRepository;
import io.devandre.furtmates.pets.control.repository.ShelterRepository;
import io.devandre.furtmates.pets.control.repository.SpeciesRepository;
import io.devandre.furtmates.pets.entity.enums.AgePet;
import io.devandre.furtmates.pets.entity.enums.GenrePet;
import io.devandre.furtmates.pets.entity.jdbc.PetFilter;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
public class PetService {

    private final BreedRepository breedRepository;
    private final SpeciesRepository speciesRepository;
    private final ShelterRepository shelterRepository;
    private final PetRepository petRepository;

    public PetService(BreedRepository breedRepository, SpeciesRepository speciesRepository,
                      ShelterRepository shelterRepository, PetRepository petRepository) {
        this.breedRepository = breedRepository;
        this.speciesRepository = speciesRepository;
        this.shelterRepository = shelterRepository;
        this.petRepository = petRepository;
    }

    public List<SpecieResponse> getAllSpecies() {
        log.info("Getting all species");
        return speciesRepository.getSpecies();
    }

    public List<BreedResponse> getAllBreeds() {
        log.info("Getting all breeds");
        return breedRepository.getBreeds();
    }

    public List<BreedResponse> getAllBreedsBySpeciesId(Integer speciesId) {
        log.info("Getting all breeds by species id: {}", speciesId);

        if (speciesRepository.existsSpeciesById(speciesId)) {
            throw new IllegalArgumentException("Species with id " + speciesId + " doesn't exist");
        }

        return breedRepository.getBreedsBySpeciesId(speciesId);
    }

    public List<ShelterResponse> getAllShelters() {
        log.info("Getting all shelters");
        return shelterRepository.getShelters();
    }

    public void createShelter(CreateShelterRequest shelter) {
        log.info("Creating shelter: {}", shelter);
        shelterRepository.createShelter(shelter);
    }

    public void updateShelter(Long id, UpdateShelterRequest shelter) {
        log.info("Updating shelter with id: {}", id);
        shelterRepository.updateShelter(id, shelter);
    }

    public void deleteShelter(Long id) {
        log.info("Deleting shelter with id: {}", id);
        shelterRepository.deleteShelter(id);
    }

    public Page<PetResponse> findPets(PetFilter filter, int page, int pageSize, String... sort) {
        Pageable pageable = PageRequest.of(page, pageSize, createSort(sort));
        log.info("Getting pets with filters: {}", filter);
        log.info("Pageable: {}", pageable);
        return petRepository.getPetsWithFilters(filter, pageable);
    }

    private Sort createSort(String... sort) {
        if (sort == null || sort.length == 0) {
            return Sort.unsorted();
        }

        List<Sort.Order> orders = new ArrayList<>();
        for (String sortParam : sort) {
            String[] parts = sortParam.split(",");
            String property = parts[0];
            Sort.Direction direction = parts.length > 1 ?
                    Sort.Direction.fromString(parts[1]) : Sort.Direction.ASC;
            orders.add(new Sort.Order(direction, property));
        }
        return Sort.by(orders);
    }

    public PetResponse getPetById(Long id) {
        log.info("Getting pet by id: {}", id);
        return petRepository.getPetById(id);
    }

    @Transactional(readOnly = false)
    public void createPet(CreatePetRequest createPetRequest) {
        log.info("Creating pet: {}", createPetRequest);

        // Validate if speciesId, breedId and shelterId exist
        validatePetRequest(createPetRequest);

        // validate the age request with the AgePet enum
        if (!AgePet.isValidAge(createPetRequest.age())) {
            throw new IllegalArgumentException("Age with value " + createPetRequest.age() + " doesn't exist");
        }

        // validate genre
        if (!GenrePet.isValidGenre(createPetRequest.genre())) {
            throw new IllegalArgumentException("Genre with value " + createPetRequest.genre() + " doesn't exist");
        }

        petRepository.createPet(createPetRequest);
    }

    private void validatePetRequest(Object petRequest) {
        Integer speciesId;
        Integer breedId;
        Integer shelterId;

        if (petRequest instanceof CreatePetRequest createPetRequest) {
            speciesId = createPetRequest.speciesId();
            breedId = createPetRequest.breedId();
            shelterId = createPetRequest.shelterId();
        } else if (petRequest instanceof UpdatePetRequest updatePetRequest) {
            speciesId = updatePetRequest.speciesId();
            breedId = updatePetRequest.breedId();
            shelterId = updatePetRequest.shelterId();
        } else {
            throw new IllegalArgumentException("Invalid pet request type");
        }

        if (!speciesRepository.existsSpeciesById(speciesId)) {
            throw new IllegalArgumentException("Species with id " + speciesId + " doesn't exist");
        }

        if (!breedRepository.existsBreedById(breedId)) {
            throw new IllegalArgumentException("Breed with id " + breedId + " doesn't exist");
        }

        if (!shelterRepository.existsShelterById(shelterId)) {
            throw new IllegalArgumentException("Shelter with id " + shelterId + " doesn't exist");
        }
    }

    @Transactional(readOnly = false)
    public void updatePet(Long id, UpdatePetRequest updatePetRequest) {
        log.info("Updating pet with id: {}", id);

        // Validate if speciesId, breedId and shelterId exist
        validatePetRequest(updatePetRequest);

        if (AgePet.isValidAge(updatePetRequest.age())) {
            throw new IllegalArgumentException("Age with value " + updatePetRequest.age() + " doesn't exist");
        }

        petRepository.updatePet(id, updatePetRequest);
    }

    @Transactional(readOnly = false)
    public void deletePet(Long id) {
        log.info("Deleting pet with id: {}", id);
        petRepository.deletePet(id);
    }

    @Transactional(readOnly = false)
    public void adoptPet(Long id) {
        log.info("Adopting pet with id: {}", id);
        petRepository.adoptPet(id);
    }
}
