package io.devandre.furtmates.users.entity.dao;

import io.devandre.furtmates.shared.security.jwt.JwtUtils;
import io.devandre.furtmates.users.control.repository.TokenRepository;
import io.devandre.furtmates.users.entity.Token;
import io.devandre.furtmates.users.entity.User;
import io.devandre.furtmates.users.entity.jdbc.JdbcTokenRepository;
import io.devandre.furtmates.users.entity.jdbc.JdbcUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

@Repository
public class SpringDataTokenRepository implements TokenRepository {

    private final JdbcTokenRepository jdbcTokenRepository;
    private final JdbcUserRepository jdbcUserRepository;
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    public SpringDataTokenRepository(JdbcTokenRepository jdbcTokenRepository,
                                     JdbcUserRepository jdbcUserRepository, JwtUtils jwtUtils,
                                     UserDetailsService userDetailsService) {
        this.jdbcTokenRepository = jdbcTokenRepository;
        this.jdbcUserRepository = jdbcUserRepository;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void createToken(Long userId, String jwt) {
        jdbcTokenRepository.save(
                Token.builder()
                        .token(jwt)
                        .userId(userId)
                        .tokenType("Bearer")
                        .expirationDate(null)
                        .expired(false)
                        .revoked(false)
                        .build()
        );
    }

    @Override
    public void deleteTokenByUserId(Long userId) {
        jdbcTokenRepository.deleteByUserId(userId);
    }

    @Override
    public String isValidToken(String jwt) {
        UserDetails userDetails = extractUserDetails(jwt);
        User user = jdbcUserRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        boolean isTokenValid = jdbcTokenRepository.existsByUserIdAndToken(user.getId(), jwt);
        if (isTokenValid && jwtUtils.validateJwtToken(jwt)) {
            return userDetails.getUsername();
        } else {
            throw new IllegalArgumentException("Invalid token");
        }
    }

    private UserDetails extractUserDetails(String jwt) {
        String email = jwtUtils.getEmailFromToken(jwt);
        return userDetailsService.loadUserByUsername(email);
    }
}
