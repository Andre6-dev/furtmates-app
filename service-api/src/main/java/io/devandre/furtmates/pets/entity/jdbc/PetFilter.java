package io.devandre.furtmates.pets.entity.jdbc;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PetFilter {
    private String name;
    private Integer speciesId;
    private Integer breedId;
    private String size;
    private String adoptionStatus;
    private Integer shelterId;
    private Boolean isSpayed;
    private String healthStatus;
    private BigDecimal minWeight;
    private BigDecimal maxWeight;
    private LocalDate arrivalDateFrom;
    private LocalDate arrivalDateTo;
}
