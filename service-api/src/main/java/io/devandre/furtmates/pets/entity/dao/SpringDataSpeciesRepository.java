package io.devandre.furtmates.pets.entity.dao;

import io.devandre.furtmates.pets.boundary.response.SpecieResponse;
import io.devandre.furtmates.pets.control.mapper.SpeciesMapper;
import io.devandre.furtmates.pets.control.repository.SpeciesRepository;
import io.devandre.furtmates.pets.entity.jdbc.JdbcSpeciesRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpringDataSpeciesRepository implements SpeciesRepository {

    private final JdbcSpeciesRepository jdbcSpeciesRepository;
    private final SpeciesMapper speciesMapper;

    public SpringDataSpeciesRepository(JdbcSpeciesRepository jdbcSpeciesRepository, SpeciesMapper speciesMapper) {
        this.jdbcSpeciesRepository = jdbcSpeciesRepository;
        this.speciesMapper = speciesMapper;
    }

    @Override
    public List<SpecieResponse> getSpecies() {
        return speciesMapper.toSpecieResponseList(jdbcSpeciesRepository.findAll());
    }

    @Override
    public boolean existsSpeciesById(Integer speciesId) {
        return jdbcSpeciesRepository.existsById(Long.valueOf(speciesId));
    }
}
