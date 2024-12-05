package io.devandre.furtmates.users.boundary.request;

import io.devandre.furtmates.users.entity.enums.UserGenre;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateProfileRequest(
        @NotNull String firstName,
        @NotNull String lastName,
        @NotNull String username,
        @NotNull @Email String email,
        @NotNull String phoneNumber,
        @NotNull String address,
        @NotNull String documentNumber,
        @NotNull Integer roleId,
        @NotNull String avatarUrl,
        @NotNull @Min(18) Integer age,
        @NotNull UserGenre genre,
        @NotNull Boolean isAdopter,
        @NotNull String bio
) {
}
