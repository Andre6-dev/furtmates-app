package io.devandre.furtmates.users.entity.dao;

import io.devandre.furtmates.shared.exception.ResourceNotFoundException;
import io.devandre.furtmates.users.boundary.response.UserResponse;
import io.devandre.furtmates.users.control.mapper.UserMapper;
import io.devandre.furtmates.users.control.repository.UserRepository;
import io.devandre.furtmates.users.entity.User;
import io.devandre.furtmates.users.entity.jdbc.JdbcUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class SpringDataUserRepository implements UserRepository {

    private final JdbcUserRepository jdbcUserRepository;
    private final UserMapper userMapper;

    public SpringDataUserRepository(JdbcUserRepository jdbcUserRepository, UserMapper userMapper) {
        this.jdbcUserRepository = jdbcUserRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void saveUser(User user) {
        jdbcUserRepository.save(user);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return jdbcUserRepository.existsByEmail(email);
    }

    @Override
    public UserResponse getUserByPublicId(UUID publicId) {
        return jdbcUserRepository.findByPublicId(publicId)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with publicId: " + publicId));
    }

    @Override
    public void updateUserEnabled(UUID publicId, boolean isEnabled) {
        jdbcUserRepository.updateEnabled(publicId, isEnabled);
    }

    @Override
    public void updateUserProfile(UUID publicId, String firstName, String lastName, String username, String email, String phoneNumber, String address, String documentNumber, Integer roleId, String avatarUrl, Integer age, String genre, Boolean isAdopter, String bio) {
        jdbcUserRepository.updateProfile(publicId, firstName, lastName, username, email, phoneNumber, address, documentNumber, roleId, avatarUrl, age, genre, isAdopter, bio);
    }

    @Override
    public void deleteUserByPublicId(UUID publicId) {
        jdbcUserRepository.deleteByPublicId(publicId);
    }

    @Override
    public Page<UserResponse> getUsers(Integer pageNo, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return jdbcUserRepository.findAll(pageable)
                .map(userMapper::toUserResponse);
    }
}
