package io.devandre.furtmates.users.boundary.controller;

import io.devandre.furtmates.shared.utils.ResponseApi;
import io.devandre.furtmates.shared.utils.ResponseController;
import io.devandre.furtmates.users.boundary.request.LoginRequest;
import io.devandre.furtmates.users.boundary.response.LoginResponse;
import io.devandre.furtmates.users.control.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
