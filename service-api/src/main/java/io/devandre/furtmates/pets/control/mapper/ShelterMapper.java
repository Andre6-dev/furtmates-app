package io.devandre.furtmates.pets.control.mapper;

import io.devandre.furtmates.pets.boundary.request.CreateShelterRequest;
import io.devandre.furtmates.pets.boundary.response.ShelterResponse;
import io.devandre.furtmates.pets.entity.Shelter;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShelterMapper {

    List<ShelterResponse> toShelterResponseList(List<Shelter> shelters);

    Shelter toShelter(CreateShelterRequest shelter);
}
