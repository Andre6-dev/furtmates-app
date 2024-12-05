package io.devandre.furtmates.users.boundary.request;

import io.devandre.furtmates.users.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoleRequest(
        @NotNull @NotBlank String name
) {
    public Role toEntity() {
        return new Role(name());
    }
}
