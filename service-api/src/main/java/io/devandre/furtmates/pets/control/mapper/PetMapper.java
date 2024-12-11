package io.devandre.furtmates.pets.control.mapper;

import io.devandre.furtmates.pets.boundary.response.PetResponse;
import io.devandre.furtmates.pets.entity.Pet;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMapper {

    PetResponse toPetResponse(Pet pet);

    List<PetResponse> toPetResponseList(List<Pet> pets);

    default Page<PetResponse> toResponsePage(Page<Pet> page) {
        return new PageImpl<>(
                toPetResponseList(page.getContent()),
                page.getPageable(),
                page.getTotalElements()
        );
    }
}
