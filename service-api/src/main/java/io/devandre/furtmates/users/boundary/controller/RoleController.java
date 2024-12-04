package io.devandre.furtmates.users.boundary.controller;

import io.devandre.furtmates.shared.utils.ApiResponse;
import io.devandre.furtmates.shared.utils.ResponseController;
import io.devandre.furtmates.users.control.service.RoleService;
import io.devandre.furtmates.users.entity.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController extends ResponseController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Role>> findRoleById(@PathVariable Long id) {
        return ok(roleService.findRoleById(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Role>>> findAllRoles() {
        return list(roleService.findAllRoles());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveRole(Role role) {
        roleService.saveRole(role);
        return created();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRoleById(@PathVariable Long id) {
        roleService.deleteRoleById(id);
        return deleted();
    }
}
