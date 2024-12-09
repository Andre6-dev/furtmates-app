package io.devandre.furtmates.users.control.service;

import io.devandre.furtmates.users.control.repository.TokenRepository;
import io.devandre.furtmates.users.entity.dao.SpringDataTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void createToken(Long userId, String jwt) {
        log.info("Creating token for user with id: {}", userId);
        tokenRepository.createToken(userId, jwt);
    }

    public void deleteTokenByUserId(Long userId) {
        log.info("Deleting token for user with id: {}", userId);
        tokenRepository.deleteTokenByUserId(userId);
    }

    public String isValidToken(String jwt) {
        log.info("Checking if token is valid");
        return tokenRepository.isValidToken(jwt);
    }
}
