package io.devandre.furtmates.pets.boundary.response;

public record BreedResponse(
        Long id,
        String name,
        Integer speciesId
) {}
