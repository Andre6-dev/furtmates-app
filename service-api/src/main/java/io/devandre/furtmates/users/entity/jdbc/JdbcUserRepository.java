package io.devandre.furtmates.users.entity.jdbc;

import io.devandre.furtmates.users.entity.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JdbcUserRepository extends ListCrudRepository<User, Long> {

    Optional<User> findByPublicId(UUID publicId);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Modifying
    @Query("UPDATE users SET is_enabled = :isEnabled WHERE public_id = :publicId")
    void updateEnabled(UUID publicId, boolean isEnabled);

}
