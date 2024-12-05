package io.devandre.furtmates.users.boundary.response;

import io.devandre.furtmates.users.entity.enums.UserGenre;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "User Data Transfer Object")
public class UserResponse {
    @Schema(description = "User's public ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID publicId;

    @Schema(description = "User's first name", example = "John")
    private String firstName;

    @Schema(description = "User's last name", example = "Doe")
    private String lastName;

    @Schema(description = "User's username", example = "johndoe")
    private String username;

    @Schema(description = "User's email", example = "john.doe@example.com")
    private String email;

    @Schema(description = "User's phone number", example = "+1234567890")
    private String phoneNumber;

    @Schema(description = "User's address")
    private String address;

    @Schema(description = "User's avatar URL")
    private String avatarUrl;

    @Schema(description = "User's age", example = "30")
    private Integer age;

    @Schema(description = "User's genre")
    private UserGenre genre;

    @Schema(description = "Whether user is enabled", example = "true")
    private Boolean isEnabled;

    @Schema(description = "Whether user is an adopter", example = "true")
    private Boolean isAdopter;

    @Schema(description = "User's bio")
    private String bio;
}