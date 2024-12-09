package io.devandre.furtmates.users.control.service;

import io.devandre.furtmates.users.control.repository.ActivationCodeRepository;
import io.devandre.furtmates.users.entity.ActivationCode;
import io.devandre.furtmates.users.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@Slf4j
public class ActivationCodeService {

    private final ActivationCodeRepository activationCodeRepository;
    private final EmailService emailService;

    public ActivationCodeService(ActivationCodeRepository activationCodeRepository, EmailService emailService) {
        this.activationCodeRepository = activationCodeRepository;
        this.emailService = emailService;
    }

    public ActivationCode getActivationCodeByKey(String key) {
        log.info("Getting activation code by key: {}", key);
        return activationCodeRepository.getByKey(key);
    }

    public void checkActivationCodeExpiration(ActivationCode activationCode) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationDate = activationCode.getExpirationDate();
        if (expirationDate.isBefore(now)) {
            deleteActivationCodeById(activationCode.getId());
            sendNewActivationCode(activationCode.getUserId());

            long minutes = ChronoUnit.MINUTES.between(expirationDate, now);

            throw new IllegalArgumentException("Activation code expired. New activation code sent. Minutes passed: " + minutes);
        }
    }

    public void deleteActivationCodeById(Long id) {
        activationCodeRepository.deleteActivationCodeById(id);
    }

    public void sendNewActivationCode(Integer userId) {
        log.info("Sending new activation code to user with id: {}", userId);
        // send new activation code to user
        ActivationCode activationCode = ActivationCode.builder()
                .userId(userId)
                .key(UUID.randomUUID().toString())
                .expirationDate(LocalDateTime.now().plusMinutes(5))
                .build();

        emailService.sendActivationCode(activationCode);
        activationCodeRepository.saveActivationCode(activationCode);
    }
}
