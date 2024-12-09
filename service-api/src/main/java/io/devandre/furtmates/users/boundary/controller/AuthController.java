package io.devandre.furtmates.users.boundary.controller;

import io.devandre.furtmates.shared.utils.ResponseApi;
import io.devandre.furtmates.shared.utils.ResponseController;
import io.devandre.furtmates.users.boundary.request.LoginRequest;
import io.devandre.furtmates.users.boundary.request.RegisterUserRequest;
import io.devandre.furtmates.users.boundary.response.ActivationCodeResponse;
import io.devandre.furtmates.users.boundary.response.LoginResponse;
import io.devandre.furtmates.users.control.service.AuthService;
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
public class AuthController extends ResponseController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/public/sign-in")
    public ResponseEntity<ResponseApi<LoginResponse>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ok(authService.authenticateUser(loginRequest));
    }

    @PostMapping("/public/sign-up")
    public ResponseEntity<ResponseApi<ActivationCodeResponse>> registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        return ok(authService.registerUser(registerUserRequest));
    }

    @PostMapping("/public/activate")
    public ResponseEntity<ResponseApi<ActivationCodeResponse>> activateUser(
            @RequestParam @Pattern(regexp = "^\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}$", message = "Invalid activation code")
            String activationCode
    ) {
        return ok(authService.activateUser(activationCode));
    }
}
