package io.devandre.furtmates.users.boundary.controller;

import io.devandre.furtmates.shared.utils.ResponseApi;
import io.devandre.furtmates.shared.utils.ResponseController;
import io.devandre.furtmates.users.boundary.response.UserResponse;
import io.devandre.furtmates.users.control.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "User management")
public class UserController extends ResponseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{publicId}")
    public ResponseEntity<ResponseApi<UserResponse>> getUserByPublicId(@PathVariable UUID publicId) {
        return ok(userService.getUserByPublicId(publicId));
    }
}
