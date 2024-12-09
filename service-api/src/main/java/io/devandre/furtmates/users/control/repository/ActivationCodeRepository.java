package io.devandre.furtmates.users.control.repository;

import io.devandre.furtmates.users.entity.ActivationCode;

public interface ActivationCodeRepository {

    void saveActivationCode(ActivationCode activationCode);

    ActivationCode getByKey(String key);

    ActivationCode getActivationCodeByUserId(Integer userId);

    void deleteActivationCodeById(Long id);
}
