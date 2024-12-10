package io.devandre.furtmates.pets.control.mapper;

import io.devandre.furtmates.pets.boundary.response.SpecieResponse;
import io.devandre.furtmates.pets.entity.Specie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpeciesMapper {

    List<SpecieResponse> toSpecieResponseList(List<Specie> species);
}
