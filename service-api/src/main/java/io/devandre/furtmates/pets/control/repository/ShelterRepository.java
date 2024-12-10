package io.devandre.furtmates.pets.control.repository;

import io.devandre.furtmates.pets.boundary.request.CreateShelterRequest;
import io.devandre.furtmates.pets.boundary.request.UpdateShelterRequest;
import io.devandre.furtmates.pets.boundary.response.ShelterResponse;
import io.devandre.furtmates.pets.entity.Shelter;

import java.util.List;

public interface ShelterRepository {

    List<ShelterResponse> getShelters();

    void createShelter(CreateShelterRequest shelter);

    void updateShelter(Long id, UpdateShelterRequest shelter);

    void deleteShelter(Long id);
}
