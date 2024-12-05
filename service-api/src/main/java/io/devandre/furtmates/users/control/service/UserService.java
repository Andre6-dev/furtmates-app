package io.devandre.furtmates.users.control.service;

import io.devandre.furtmates.users.boundary.response.UserResponse;
import io.devandre.furtmates.users.control.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
