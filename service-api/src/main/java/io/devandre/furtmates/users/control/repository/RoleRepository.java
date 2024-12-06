package io.devandre.furtmates.users.control.repository;

import io.devandre.furtmates.users.entity.Role;

import java.util.List;

public interface RoleRepository {

    void saveRole(Role role);

    boolean existsRoleById(Long id);

    boolean existsRoleByName(String name);

    Role findRoleById(Long id);

    List<Role> findRolesByUserId(Long userId);

    List<Role> findAllRoles();

    void deleteRoleById(Long id);
}
