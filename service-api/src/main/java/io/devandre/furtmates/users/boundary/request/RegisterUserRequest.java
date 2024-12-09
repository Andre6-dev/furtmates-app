package io.devandre.furtmates.users.boundary.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @NotNull String firstName,
        @NotNull String lastName,
        @Email @NotNull String email,
        @Size(min = 5, max = 25) @NotNull String password,
        @Size(max = 8) @NotNull String documentNumber,
        String phoneNumber,
        String address,
        String genre,
        Integer age,
        String bio
) {}
