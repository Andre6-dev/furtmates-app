package io.devandre.furtmates.users.entity.jdbc;

import io.devandre.furtmates.users.entity.ActivationCode;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JdbcActivationCodeRepository extends ListCrudRepository<ActivationCode, Long> {

    Optional<ActivationCode> findActivationCodeByKey(String key);

    @Query("SELECT * FROM furtmates_schema.verification_code WHERE user_id = :id")
    Optional<ActivationCode> findActivationCodeByUserEmail(Integer id);
}
