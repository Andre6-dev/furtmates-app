package io.devandre.furtmates.users.entity.jdbc;

import io.devandre.furtmates.users.entity.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JdbcUserRepository extends ListCrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {

    Optional<User> findByPublicId(UUID publicId);

    Optional<User> findByEmail(String email);

    @Query("SELECT id FROM furtmates_schema.users WHERE public_id = :publicId")
    Long getPublicUserId(UUID publicId);

    boolean existsByEmail(String email);

    @Modifying
    @Query("UPDATE furtmates_schema.users SET is_enabled = :isEnabled WHERE public_id = :publicId")
    void updateEnabled(UUID publicId, boolean isEnabled);

    @Modifying
    @Query("UPDATE furtmates_schema.users SET first_name = :firstName, last_name = :lastName, username = :username, email = :email, phone_number = :phoneNumber, address = :address, document_number = :documentNumber, role_id = :roleId, avatar_url = :avatarUrl, age = :age, genre = :genre, is_adopter = :isAdopter, bio = :bio WHERE public_id = :publicId")
    void updateProfile(@Param(value = "publicId") UUID publicId,
                          @Param(value = "firstName") String firstName,
                          @Param(value = "lastName") String lastName,
                          @Param(value = "username") String username,
                          @Param(value = "email") String email,
                          @Param(value = "phoneNumber") String phoneNumber,
                          @Param(value = "address") String address,
                          @Param(value = "documentNumber") String documentNumber,
                          @Param(value = "roleId") Integer roleId,
                          @Param(value = "avatarUrl") String avatarUrl,
                          @Param(value = "age") Integer age,
                          @Param(value = "genre") String genre,
                          @Param(value = "isAdopter") Boolean isAdopter,
                          @Param(value = "bio") String bio);

    @Modifying
    @Query("DELETE FROM users WHERE public_id = :publicId")
    void deleteByPublicId(UUID publicId);

    Optional<User> findByUsername(String username);
}
