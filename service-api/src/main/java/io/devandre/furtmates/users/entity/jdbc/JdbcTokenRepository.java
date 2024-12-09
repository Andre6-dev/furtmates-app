package io.devandre.furtmates.users.entity.jdbc;

import io.devandre.furtmates.users.entity.Token;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JdbcTokenRepository extends ListCrudRepository<Token, Long> {

    Optional<Token> findByToken(String token);

    @Query("SELECT * FROM furtmates_schema.tokens WHERE user_id = :userId")
    Optional<Token> findByUserId(Long userId);

    @Modifying
    @Query("DELETE FROM furtmates_schema.tokens WHERE user_id = :userId")
    void deleteByUserId(Long userId);

    boolean existsByUserIdAndToken(Long userId, String token);
}
