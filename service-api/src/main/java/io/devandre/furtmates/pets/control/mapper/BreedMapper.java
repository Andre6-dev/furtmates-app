package io.devandre.furtmates.pets.control.mapper;

import io.devandre.furtmates.pets.boundary.response.BreedResponse;
import io.devandre.furtmates.pets.entity.Breed;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BreedMapper {

    List<BreedResponse> toBreedResponseList(List<Breed> breeds);
}
