package io.devandre.furtmates.users.entity.jdbc;

import io.devandre.furtmates.users.entity.Role;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JdbcRoleRepository extends ListCrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

    boolean existsByName(String name);

    @Query("SELECT * FROM furtmates_schema.roles INNER JOIN furtmates_schema.users u on roles.id = u.role_id WHERE u.id = :userId")
    List<Role> findRolesByUserId(Long userId);
}
