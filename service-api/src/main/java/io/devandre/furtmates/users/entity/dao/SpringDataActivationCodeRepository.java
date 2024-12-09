package io.devandre.furtmates.users.entity.dao;

import io.devandre.furtmates.shared.exception.ResourceNotFoundException;
import io.devandre.furtmates.users.control.repository.ActivationCodeRepository;
import io.devandre.furtmates.users.entity.ActivationCode;
import io.devandre.furtmates.users.entity.jdbc.JdbcActivationCodeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SpringDataActivationCodeRepository implements ActivationCodeRepository {

    private final JdbcActivationCodeRepository jdbcActivationCodeRepository;

    public SpringDataActivationCodeRepository(JdbcActivationCodeRepository jdbcActivationCodeRepository) {
        this.jdbcActivationCodeRepository = jdbcActivationCodeRepository;
    }


    @Override
    public void saveActivationCode(ActivationCode activationCode) {
        jdbcActivationCodeRepository.save(
                ActivationCode.builder()
                        .userId(activationCode.getUserId())
                        .key(activationCode.getKey())
                        .expirationDate(activationCode.getExpirationDate())
                        .build()
        );
    }

    @Override
    public ActivationCode getByKey(String key) {
        return jdbcActivationCodeRepository.findActivationCodeByKey(key).orElseThrow(
                () -> new ResourceNotFoundException("Activation code not found")
        );
    }

    @Override
    public ActivationCode getActivationCodeByUserId(Integer userId) {
        return jdbcActivationCodeRepository.findActivationCodeByUserEmail(userId).orElseThrow(
                () -> new ResourceNotFoundException("Activation code not found with user id: " + userId)
        );
    }

    @Override
    public void deleteActivationCodeById(Long id) {
        jdbcActivationCodeRepository.deleteById(id);
    }
}
