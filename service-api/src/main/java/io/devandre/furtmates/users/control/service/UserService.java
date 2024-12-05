package io.devandre.furtmates.users.control.service;

import io.devandre.furtmates.users.boundary.request.UpdateProfileRequest;
import io.devandre.furtmates.users.boundary.response.UserResponse;
import io.devandre.furtmates.users.control.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse getUserByPublicId(UUID publicId) {
        log.info("Getting user by publicId: {}", publicId);
        return userRepository.getUserByPublicId(publicId);
    }

    public Page<UserResponse> getPagedUsers(Integer pageNo, Integer pageSize, String sortBy, String sortDirection) {
        log.info(
                "Getting paged users with pageNo: {}, pageSize: {}, sortBy: {}, sortDirection: {}",
                pageNo,
                pageSize,
                sortBy,
                sortDirection);

        //Validate sort field to prevent SQL injection
        Set<String> allowedFields = Set.of(
                "id",
                "firstName",
                "lastName",
                "email",
                "username",
                "phoneNumber",
                "address",
                "documentNumber",
                "roleId",
                "genre",
                "isAdopter");

        if (!allowedFields.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort field: " + sortBy);
        }

        return userRepository.getUsers(pageNo, pageSize, sortBy, sortDirection);
    }

    @Transactional(readOnly = false)
    public Long updateUserEnabled(UUID publicId, Boolean isEnabled) {
        log.info("Updating user enabled by publicId: {}", publicId);
        Long userId = userRepository.getPublicUserId(publicId);

        if (userId == null) {
            throw new IllegalArgumentException("User not found with publicId: " + publicId);
        }

        userRepository.updateUserEnabled(publicId, isEnabled);
        return userId;
    }

    @Transactional(readOnly = false)
    public Long updateUserProfile(UUID publicId, UpdateProfileRequest request) {
        log.info("Updating user profile by publicId: {}", publicId);

        Long userId = userRepository.getPublicUserId(publicId);

        if (userId == null) {
            throw new IllegalArgumentException("User not found with publicId: " + publicId);
        }

        userRepository.updateUserProfile(
                publicId,
                request.firstName(),
                request.lastName(),
                request.username(),
                request.email(),
                request.phoneNumber(),
                request.address(),
                request.documentNumber(),
                request.roleId(),
                request.avatarUrl(),
                request.age(),
                request.genre().toString(),
                request.isAdopter(),
                request.bio());

        return userId;
    }

    @Transactional(readOnly = false)
    public void deleteUserByPublicId(UUID publicId) {
        log.info("Deleting user by publicId: {}", publicId);
        userRepository.deleteUserByPublicId(publicId);
    }
}
