package io.devandre.furtmates.pets.control.repository;

import io.devandre.furtmates.pets.boundary.response.SpecieResponse;

import java.util.List;

public interface SpeciesRepository {

    List<SpecieResponse> getSpecies();

    boolean existsSpeciesById(Integer speciesId);
}
