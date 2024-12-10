package io.devandre.furtmates.pets.boundary.request;

public record UpdateShelterRequest(
        String name,
        String phoneNumber,
        String address,
        String email,
        Integer updatedBy
) {
}
