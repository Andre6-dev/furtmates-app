package io.devandre.furtmates.users.boundary.controller;

import io.devandre.furtmates.shared.utils.ResponseApi;
import io.devandre.furtmates.shared.utils.ResponseController;
import io.devandre.furtmates.users.boundary.request.UpdateProfileRequest;
import io.devandre.furtmates.users.boundary.response.UserResponse;
import io.devandre.furtmates.users.control.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    @GetMapping
    public ResponseEntity<ResponseApi<List<UserResponse>>> getUsers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        return withPagination(userService.getPagedUsers(pageNo, pageSize, sortBy, sortDirection));
    }

    @PutMapping("/{publicId}")
    public ResponseEntity<ResponseApi<Long>> updateUserEnabled(@PathVariable UUID publicId, @RequestParam Boolean isEnabled) {
        return ok(userService.updateUserEnabled(publicId, isEnabled));
    }

    @PutMapping("/{publicId}/profile")
    public ResponseEntity<ResponseApi<Long>> updateUxserProfile(@PathVariable UUID publicId, @RequestBody @Valid UpdateProfileRequest request) {
        return ok(userService.updateUserProfile(publicId, request));
    }

    @DeleteMapping("/{publicId}")
    public ResponseEntity<ResponseApi<Void>> deleteUserByPublicId(@PathVariable UUID publicId) {
        userService.deleteUserByPublicId(publicId);
        return deleted();
    }
}
