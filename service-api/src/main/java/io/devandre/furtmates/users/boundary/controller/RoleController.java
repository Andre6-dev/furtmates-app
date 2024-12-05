package io.devandre.furtmates.users.boundary.controller;

import io.devandre.furtmates.shared.exception.ApiError;
import io.devandre.furtmates.shared.utils.ResponseApi;
import io.devandre.furtmates.shared.utils.ResponseController;
import io.devandre.furtmates.users.boundary.request.RoleRequest;
import io.devandre.furtmates.users.control.service.RoleService;
import io.devandre.furtmates.users.entity.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@Tag(name = "Roles", description = "Role management")
public class RoleController extends ResponseController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Operation(summary = "Find role by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Role found",
                    content = @Content(schema = @Schema(implementation = Role.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid role id",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<Role>> findRoleById(@PathVariable Long id) {
        return ok(roleService.findRoleById(id));
    }

    @Operation(summary = "Find all roles")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Roles found",
                    content = @Content(schema = @Schema(implementation = Role.class))
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No roles found",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            )
    })
    @GetMapping
    public ResponseEntity<ResponseApi<List<Role>>> findAllRoles() {
        return list(roleService.findAllRoles());
    }

    @Operation(summary = "Save role")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Role created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid role request",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            )
    })
    @PostMapping
    public ResponseEntity<ResponseApi<Void>> saveRole(@RequestBody @Valid RoleRequest role) {
        roleService.saveRole(role);
        return created();
    }

    @Operation(summary = "Delete role by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Role deleted"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid role id",
                    content = @Content(schema = @Schema(implementation = ApiError.class))
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi<Void>> deleteRoleById(@PathVariable Long id) {
        roleService.deleteRoleById(id);
        return deleted();
    }
}
