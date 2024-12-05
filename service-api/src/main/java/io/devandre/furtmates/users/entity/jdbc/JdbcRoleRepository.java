package io.devandre.furtmates.users.entity.jdbc;

import io.devandre.furtmates.users.entity.Role;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JdbcRoleRepository extends ListCrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

    boolean existsByName(String name);
}
