package io.devandre.furtmates.users.control.service;

import io.devandre.furtmates.shared.exception.InvalidCredentialsException;
import io.devandre.furtmates.shared.security.jwt.JwtUtils;
import io.devandre.furtmates.shared.security.services.UserDetailsImpl;
import io.devandre.furtmates.users.boundary.request.LoginRequest;
import io.devandre.furtmates.users.boundary.request.RegisterUserRequest;
import io.devandre.furtmates.users.boundary.response.ActivationCodeResponse;
import io.devandre.furtmates.users.boundary.response.LoginResponse;
import io.devandre.furtmates.users.control.repository.UserRepository;
import io.devandre.furtmates.users.entity.ActivationCode;
import io.devandre.furtmates.users.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@Slf4j
public class AuthService {

    private static final String defaultAvatarUrl = "https://images.squarespace-cdn.com/content/v1/5eb48d3fef49df153d320521/1618032587947-LCJQDE1Q9CGKRRM774H2/Daft+Punk+Toy+Face+II+.jpg";

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final ActivationCodeService activationCodeService;

    public AuthService(JwtUtils jwtUtils, AuthenticationManager authenticationManager, UserRepository userRepository,
                       TokenService tokenService, PasswordEncoder passwordEncoder,
                       ActivationCodeService activationCodeApplicationService) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.activationCodeService = activationCodeApplicationService;
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

        long expirationMillis = jwtUtils.getJwtExpirationMs();

        String formattedExpirationTime = Instant.ofEpochMilli(expirationMillis)
                // UTF8 time zone
                .atZone(ZoneId.of("UTC"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Integer userId = userRepository.getIdByEmail(request.email());

        tokenService.deleteTokenByUserId(userId.longValue());
        tokenService.createToken(userId.longValue(), jwt);
        log.info("Token created for user with email: {}", request.email());

        // Collect roles from UserDetails
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        // Create a new LoginResponse object
        return LoginResponse.builder()
                .jwtToken(jwt)
                .email(userDetails.getUsername())
                .roles(roles)
                .expirationDate(formattedExpirationTime)
                .build();
    }

    @Transactional(readOnly = false)
    public ActivationCodeResponse registerUser(RegisterUserRequest request) {
        log.info("Registering user with email: {}", request.email());

        if (userRepository.existsUserByEmail(request.email())) {
            log.error("User already exists with email: {}", request.email());
            throw new IllegalArgumentException("User already exists with email: " + request.email());
        }

        if (userRepository.existsUserByDocumentNumber(request.documentNumber())) {
            log.error("User already exists with document number: {}", request.documentNumber());
            throw new IllegalArgumentException("User already exists with document number: " + request.documentNumber());
        }

        String username = request.firstName() + request.lastName();

        User newUser = User.builder()
                .publicId(UUID.randomUUID())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .username(username)
                .email(request.email())
                .passwordHash(passwordEncoder.encode(request.password()))
                .phoneNumber(request.phoneNumber())
                .address(request.address())
                .documentNumber(request.documentNumber())
                .roleId(3) // Adopter role
                .avatarUrl(defaultAvatarUrl)
                .age(request.age())
                .genre(request.genre())
                .isAdopter(true) // default value
                .isEnabled(false) // Should be enabled after email verification
                .bio(request.bio())
                .build();

        userRepository.saveUser(newUser);

        activationCodeService.sendNewActivationCode(Math.toIntExact(newUser.getId()));
        log.info("Activation code sent to user with email: {}", request.email());

        return ActivationCodeResponse.builder()
                .message("Activation code sent to email: " + request.email())
                .build();
    }

    @Transactional(readOnly = false)
    public ActivationCodeResponse activateUser(String key) {
        ActivationCode activationCode = activationCodeService.getActivationCodeByKey(key);

        activationCodeService.checkActivationCodeExpiration(activationCode);

        User user = userRepository.getUserById(Long.valueOf(activationCode.getUserId()));
        userRepository.updateUserEnabled(user.getPublicId(), true);
        log.info("User with email: {} has been enabled", user.getEmail());
        activationCodeService.deleteActivationCodeById(activationCode.getId());
        log.info("Activation code with id: {} has been deleted", activationCode.getId());

        log.info("User with email: {} has successfully activated, ", user.getEmail());
        return ActivationCodeResponse.builder()
                .message("User with email: " + user.getEmail() + " has successfully activated")
                .build();
    }
}
