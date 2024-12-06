package io.devandre.furtmates.users.entity.dao;

import io.devandre.furtmates.shared.exception.ResourceNotFoundException;
import io.devandre.furtmates.users.control.repository.RoleRepository;
import io.devandre.furtmates.users.entity.Role;
import io.devandre.furtmates.users.entity.jdbc.JdbcRoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpringDataRoleRepository implements RoleRepository {

    private final JdbcRoleRepository jdbcRoleRepository;

    public SpringDataRoleRepository(JdbcRoleRepository jdbcRoleRepository) {
        this.jdbcRoleRepository = jdbcRoleRepository;
    }

    @Override
    public void saveRole(Role role) {
        jdbcRoleRepository.save(role);
    }

    @Override
    public boolean existsRoleById(Long id) {
        return jdbcRoleRepository.existsById(id);
    }

    @Override
    public boolean existsRoleByName(String name) {
        return jdbcRoleRepository.existsByName(name);
    }

    @Override
    public Role findRoleById(Long id) {
        return jdbcRoleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));
    }

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        return jdbcRoleRepository.findRolesByUserId(userId);
    }

    @Override
    public List<Role> findAllRoles() {
        return jdbcRoleRepository.findAll();
    }

    @Override
    public void deleteRoleById(Long id) {
        jdbcRoleRepository.deleteById(id);
    }
}
