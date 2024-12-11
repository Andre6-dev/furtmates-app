package io.devandre.furtmates.pets.boundary.response;

public record PetResponse(
        Long id,
        String name,
        Integer speciesId,
        Integer breedId,
        String size,
        String adoptionStatus,
        Integer shelterId,
        Boolean isSpayed,
        String healthStatus,
        String weight,
        String arrivalDate
) {
}
