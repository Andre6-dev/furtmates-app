package io.devandre.furtmates.users.control.service;

import io.devandre.furtmates.users.entity.jdbc.JdbcRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final JdbcRoleRepository jdbcRoleRepository;

    public UserService(JdbcRoleRepository jdbcRoleRepository) {
        this.jdbcRoleRepository = jdbcRoleRepository;
    }

    public void saveRole() {
    }
}
