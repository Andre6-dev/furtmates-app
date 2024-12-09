package io.devandre.furtmates.users.boundary.controller;

import io.devandre.furtmates.shared.utils.ResponseApi;
import io.devandre.furtmates.shared.utils.ResponseController;
import io.devandre.furtmates.users.boundary.request.LoginRequest;
import io.devandre.furtmates.users.boundary.request.RegisterUserRequest;
import io.devandre.furtmates.users.boundary.response.ActivationCodeResponse;
import io.devandre.furtmates.users.boundary.response.LoginResponse;
import io.devandre.furtmates.users.control.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public class AuthController extends ResponseController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
            summary = "User sign in",
            description = "Authenticate user and return JWT token",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful authentication",
                            content = @Content(schema = @Schema(implementation = LoginResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Authentication failed"
                    )
            }
    )
    @SecurityRequirements  // This removes security requirements for this endpoint
    @PostMapping("/public/sign-in")
    public ResponseEntity<ResponseApi<LoginResponse>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ok(authService.authenticateUser(loginRequest));
    }

    @Operation(
            summary = "User sign up",
            description = "Register a new user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User registered successfully",
                            content = @Content(schema = @Schema(implementation = ActivationCodeResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request"
                    )
            }
    )
    @SecurityRequirements  // This removes security requirements for this endpoint
    @PostMapping("/public/sign-up")
    public ResponseEntity<ResponseApi<ActivationCodeResponse>> registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        return ok(authService.registerUser(registerUserRequest));
    }

    @Operation(
            summary = "Activate user",
            description = "Activate user account",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User account activated",
                            content = @Content(schema = @Schema(implementation = ActivationCodeResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request"
                    )
            }
    )
    @PostMapping("/public/activate")
    public ResponseEntity<ResponseApi<ActivationCodeResponse>> activateUser(
            @RequestParam @Pattern(regexp = "^\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}$", message = "Invalid activation code")
            String activationCode
    ) {
        return ok(authService.activateUser(activationCode));
    }
}
