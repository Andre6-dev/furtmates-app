package io.devandre.furtmates.pets.boundary.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CreateShelterRequest(
        @NotNull String name,
        @NotNull String phoneNumber,
        @NotNull String address,
        @NotNull @Email String email
        ) {
}
