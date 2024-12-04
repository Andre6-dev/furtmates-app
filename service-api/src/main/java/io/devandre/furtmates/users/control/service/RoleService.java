package io.devandre.furtmates.users.control.service;

import io.devandre.furtmates.users.control.repository.RoleRepository;
import io.devandre.furtmates.users.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void saveRole(Role role) {
        log.info("Saving role with name: {}", role.getName());

        // The role exists in the database
        if (roleRepository.existsRoleById(role.getId())) {
            log.error("Role already exists with id: {}", role.getId());
            return;
        }
        roleRepository.saveRole(role);
    }

    public Role findRoleById(Long id) {
        log.info("Finding role with id: {}", id);
        return roleRepository.findRoleById(id);
    }

    public List<Role> findAllRoles() {
        log.info("Finding all roles");
        return roleRepository.findAllRoles();
    }

    public void deleteRoleById(Long id) {
        log.info("Deleting role with id: {}", id);

        // The role does not exist in the database
        if (!roleRepository.existsRoleById(id)) {
            log.error("Role does not exist with id: {}", id);
            return;
        }

        roleRepository.deleteRoleById(id);
    }
}
