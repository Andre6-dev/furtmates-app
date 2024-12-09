package io.devandre.furtmates.users.control.repository;

import io.devandre.furtmates.users.boundary.response.UserResponse;
import io.devandre.furtmates.users.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface UserRepository {

    void saveUser(User user);

    User getUserByEmail(String email);

    User getUserById(Long id);

    Integer getIdByEmail(String email);

    boolean existsUserByEmail(String email);

    UserResponse getUserByPublicId(UUID publicId);

    Long getPublicUserId(UUID publicId);

    void updateUserEnabled(UUID publicId, boolean isEnabled);

    void updateUserProfile(UUID publicId,
                           String firstName,
                           String lastName,
                           String username,
                           String email,
                           String phoneNumber,
                           String address,
                           String documentNumber,
                           Integer roleId,
                           String avatarUrl,
                           Integer age,
                           String genre,
                           Boolean isAdopter,
                           String bio);

    void deleteUserByPublicId(UUID publicId);

    Page<UserResponse> getUsers(Integer pageNo, Integer pageSize, String sortBy, String sortDirection);

    boolean existsUserByDocumentNumber(@Size(max = 8) @NotNull String s);
}
