package io.devandre.furtmates.pets.entity.dao;

import io.devandre.furtmates.pets.boundary.response.BreedResponse;
import io.devandre.furtmates.pets.control.mapper.BreedMapper;
import io.devandre.furtmates.pets.control.repository.BreedRepository;
import io.devandre.furtmates.pets.entity.jdbc.JdbcBreedRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpringDataBreedRepository implements BreedRepository {

    private final JdbcBreedRepository jdbcBreedRepository;
    private final BreedMapper breedMapper;

    public SpringDataBreedRepository(JdbcBreedRepository jdbcBreedRepository, BreedMapper breedMapper) {
        this.jdbcBreedRepository = jdbcBreedRepository;
        this.breedMapper = breedMapper;
    }

    @Override
    public List<BreedResponse> getBreeds() {
        return breedMapper.toBreedResponseList(jdbcBreedRepository.findAll());
    }

    @Override
    public List<BreedResponse> getBreedsBySpeciesId(Integer speciesId) {
        return breedMapper.toBreedResponseList(jdbcBreedRepository.findBySpeciesId(speciesId));
    }
}
