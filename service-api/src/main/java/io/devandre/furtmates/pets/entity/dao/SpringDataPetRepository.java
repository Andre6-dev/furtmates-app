package io.devandre.furtmates.pets.entity.dao;

import io.devandre.furtmates.pets.boundary.response.PetResponse;
import io.devandre.furtmates.pets.control.mapper.PetMapper;
import io.devandre.furtmates.pets.control.repository.PetRepository;
import io.devandre.furtmates.pets.entity.Pet;
import io.devandre.furtmates.pets.entity.jdbc.JdbcPetRepository;
import io.devandre.furtmates.pets.entity.jdbc.PetFilter;
import io.devandre.furtmates.pets.entity.jdbc.PetRowMapper;
import io.devandre.furtmates.pets.entity.jdbc.QueryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class SpringDataPetRepository implements PetRepository {

    private final PetMapper petMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final PetRowMapper petRowMapper;

    public SpringDataPetRepository(PetMapper petMapper,
                                   NamedParameterJdbcTemplate jdbcTemplate, PetRowMapper petRowMapper) {
        this.petMapper = petMapper;
        this.jdbcTemplate = jdbcTemplate;
        this.petRowMapper = petRowMapper;
    }

    @Override
    public Page<PetResponse> getPetsWithFilters(PetFilter filter, Pageable pageable) {
        QueryBuilder queryBuilder = new QueryBuilder();
        MapSqlParameterSource params = new MapSqlParameterSource();

        // Build base query
        StringBuilder sql = queryBuilder
                .select("SELECT p.*")
                .from("FROM furtmates_schema.pets p")
                .where(filter, params)
                .sort(pageable.getSort())
                .build();

        String countSql = "SELECT COUNT(*) " + queryBuilder.getFromClause() + queryBuilder.getWhereClause();
        Long total = Optional.ofNullable(jdbcTemplate.queryForObject(countSql, params, Long.class)).orElse(0L);

        // Add pagination
        sql.append(" LIMIT :limit OFFSET :offset");
        params.addValue("limit", pageable.getPageSize());
        params.addValue("offset", pageable.getOffset());

        // log the sql query
        log.info("Executing query: {}", sql);

        // Execute query
        List<Pet> pets = jdbcTemplate.query(sql.toString(), params, petRowMapper);

        return petMapper.toResponsePage(new PageImpl<>(pets, pageable, total));
    }
}
