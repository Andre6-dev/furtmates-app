package io.devandre.furtmates.pets.control.repository;

import io.devandre.furtmates.pets.boundary.response.BreedResponse;

import java.util.List;

public interface BreedRepository {

    List<BreedResponse> getBreeds();
    List<BreedResponse> getBreedsBySpeciesId(Integer speciesId);
}
