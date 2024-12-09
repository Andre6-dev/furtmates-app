package io.devandre.furtmates.users.control.repository;

public interface TokenRepository {

    void createToken(Long userId, String jwt);
    void deleteTokenByUserId(Long userId);
    String isValidToken(String jwt);

}
