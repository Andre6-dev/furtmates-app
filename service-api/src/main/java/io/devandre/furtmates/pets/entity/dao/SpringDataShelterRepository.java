package io.devandre.furtmates.pets.entity.dao;

import io.devandre.furtmates.pets.boundary.request.CreateShelterRequest;
import io.devandre.furtmates.pets.boundary.request.UpdateShelterRequest;
import io.devandre.furtmates.pets.boundary.response.ShelterResponse;
import io.devandre.furtmates.pets.control.mapper.ShelterMapper;
import io.devandre.furtmates.pets.control.repository.ShelterRepository;
import io.devandre.furtmates.pets.entity.jdbc.JdbcShelterRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpringDataShelterRepository implements ShelterRepository {

    private final JdbcShelterRepository jdbcShelterRepository;
    private final ShelterMapper shelterMapper;

    public SpringDataShelterRepository(JdbcShelterRepository jdbcShelterRepository, ShelterMapper shelterMapper) {
        this.jdbcShelterRepository = jdbcShelterRepository;
        this.shelterMapper = shelterMapper;
    }

    @Override
    public List<ShelterResponse> getShelters() {
        return shelterMapper.toShelterResponseList(jdbcShelterRepository.findAll());
    }

    @Override
    public void createShelter(CreateShelterRequest shelter) {
        jdbcShelterRepository.save(shelterMapper.toShelter(shelter));
    }

    @Override
    public void updateShelter(Long id, UpdateShelterRequest shelter) {
        jdbcShelterRepository.updateShelter(id, shelter.name(), shelter.phoneNumber(), shelter.address(), shelter.email());
    }

    @Override
    public void deleteShelter(Long id) {
        jdbcShelterRepository.deleteById(id);
    }
}
