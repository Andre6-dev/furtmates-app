package io.devandre.furtmates.pets.boundary.controller;

import io.devandre.furtmates.pets.boundary.request.CreateShelterRequest;
import io.devandre.furtmates.pets.boundary.request.UpdateShelterRequest;
import io.devandre.furtmates.pets.boundary.response.BreedResponse;
import io.devandre.furtmates.pets.boundary.response.ShelterResponse;
import io.devandre.furtmates.pets.boundary.response.SpecieResponse;
import io.devandre.furtmates.pets.control.service.PetService;
import io.devandre.furtmates.shared.utils.ResponseApi;
import io.devandre.furtmates.shared.utils.ResponseController;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
@Tag(name = "Pets", description = "Endpoints for managing pets")
public class PetController extends ResponseController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/species")
    public ResponseEntity<ResponseApi<List<SpecieResponse>>> getAllSpecies() {
        return ok(petService.getAllSpecies());
    }

    @GetMapping("/breeds")
    public ResponseEntity<ResponseApi<List<BreedResponse>>> getAllBreeds() {
        return ok(petService.getAllBreeds());
    }

    @GetMapping("/breeds/species/{speciesId}")
    public ResponseEntity<ResponseApi<List<BreedResponse>>> getAllBreedsBySpeciesId(@PathVariable Integer speciesId) {
        return ok(petService.getAllBreedsBySpeciesId(speciesId));
    }

    @GetMapping("/shelters")
    public ResponseEntity<ResponseApi<List<ShelterResponse>>> getAllShelters() {
        return ok(petService.getAllShelters());
    }

    @PostMapping("/shelters")
    public ResponseEntity<ResponseApi<Void>> createShelter(@Valid @RequestBody CreateShelterRequest createShelterRequest) {
        petService.createShelter(createShelterRequest);
        return created();
    }

    @PutMapping("/shelters/{id}")
    public ResponseEntity<ResponseApi<Void>> updateShelter(@PathVariable Long id, @Valid @RequestBody UpdateShelterRequest updateShelterRequest) {
        petService.updateShelter(id, updateShelterRequest);
        return created();
    }

    @DeleteMapping("/shelters/{id}")
    public ResponseEntity<ResponseApi<Void>> deleteShelter(@PathVariable Long id) {
        petService.deleteShelter(id);
        return deleted();
    }
}
