package io.devandre.furtmates.users.control.service;

import io.devandre.furtmates.shared.exception.InvalidCredentialsException;
import io.devandre.furtmates.shared.security.jwt.JwtUtils;
import io.devandre.furtmates.shared.security.services.UserDetailsImpl;
import io.devandre.furtmates.users.boundary.request.LoginRequest;
import io.devandre.furtmates.users.boundary.response.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
public class AuthService {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthService(JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @Transactional(readOnly = false)
    public LoginResponse authenticateUser(LoginRequest request) {
        log.info("Authenticating user with email: {}", request.email());
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        } catch (DisabledException e) {
            throw new DisabledException("User is disabled");
        } catch (BadCredentialsException e) {
            log.error("Invalid credentials for user with email: {}", request.email());
            throw new InvalidCredentialsException("Invalid credentials");
        }

        // Set the authentication object in the security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateTokenFromUsername(userDetails);

        // Collect roles from UserDetails
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        // Create a new LoginResponse object
        return LoginResponse.builder()
                .jwtToken(jwt)
                .email(userDetails.getUsername())
                .roles(roles)
                .build();
    }
}
