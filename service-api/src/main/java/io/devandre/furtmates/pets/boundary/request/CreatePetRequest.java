package io.devandre.furtmates.pets.boundary.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreatePetRequest(
        @NotNull String name,
        @NotNull Integer speciesId,
        @NotNull Integer breedId,
        @NotNull String age,
        @NotNull String genre,
        @NotNull BigDecimal weight,
        @NotNull String description,
        @NotNull String size,
        @NotNull String color,
        @NotNull String healthStatus,
        @NotNull String characteristics,
        @NotNull Integer shelterId,
        @NotNull LocalDate arrivalDate,
        @NotNull String goodWith,
        @NotNull Boolean isSpayed
) {
}
