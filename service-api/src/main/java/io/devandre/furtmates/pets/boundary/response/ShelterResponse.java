package io.devandre.furtmates.pets.boundary.response;

public record ShelterResponse(
        Long id,
        String name,
        String phoneNumber,
        String address,
        String email
) {
}
