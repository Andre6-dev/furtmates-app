package io.devandre.furtmates.pets.control.service;

import io.devandre.furtmates.pets.boundary.request.CreateShelterRequest;
import io.devandre.furtmates.pets.boundary.request.UpdateShelterRequest;
import io.devandre.furtmates.pets.boundary.response.BreedResponse;
import io.devandre.furtmates.pets.boundary.response.PetResponse;
import io.devandre.furtmates.pets.boundary.response.ShelterResponse;
import io.devandre.furtmates.pets.boundary.response.SpecieResponse;
import io.devandre.furtmates.pets.control.repository.BreedRepository;
import io.devandre.furtmates.pets.control.repository.PetRepository;
import io.devandre.furtmates.pets.control.repository.ShelterRepository;
import io.devandre.furtmates.pets.control.repository.SpeciesRepository;
import io.devandre.furtmates.pets.entity.jdbc.PetFilter;
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
}
