package io.devandre.furtmates.pets.control.service;

import io.devandre.furtmates.pets.boundary.request.CreateShelterRequest;
import io.devandre.furtmates.pets.boundary.request.UpdateShelterRequest;
import io.devandre.furtmates.pets.boundary.response.BreedResponse;
import io.devandre.furtmates.pets.boundary.response.ShelterResponse;
import io.devandre.furtmates.pets.boundary.response.SpecieResponse;
import io.devandre.furtmates.pets.control.repository.BreedRepository;
import io.devandre.furtmates.pets.control.repository.ShelterRepository;
import io.devandre.furtmates.pets.control.repository.SpeciesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
public class PetService {

    private final BreedRepository breedRepository;
    private final SpeciesRepository speciesRepository;
    private final ShelterRepository shelterRepository;

    public PetService(BreedRepository breedRepository, SpeciesRepository speciesRepository,
                      ShelterRepository shelterRepository) {
        this.breedRepository = breedRepository;
        this.speciesRepository = speciesRepository;
        this.shelterRepository = shelterRepository;
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
}
