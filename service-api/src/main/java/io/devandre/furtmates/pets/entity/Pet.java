package io.devandre.furtmates.pets.entity;

import io.devandre.furtmates.shared.persistence.AbstractAuditingEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Table(schema = "furtmates_schema", name = "pets")
public class Pet extends AbstractAuditingEntity {

    @Id
    private Long id;
    private String name;
    private Integer speciesId;
    private Integer breedId;
    private String age;
    private String genre;
    private BigDecimal weight;
    private String description;
    private String size;
    private String color;
    private String healthStatus;
    private String characteristics;
    private String adoptionStatus;
    private Integer shelterId;
    private LocalDate arrivalDate;
    private String goodWith;
    private Boolean isSpayed;
    private Integer updatedBy;


    public Pet() {}

    public Pet(Long id,
               String name,
               Integer speciesId,
               Integer breedId,
               String age,
               String genre,
               BigDecimal weight,
               String description,
               String size,
               String color,
               String healthStatus,
               String characteristics,
               String adoptionStatus,
               Integer shelterId,
               LocalDate arrivalDate,
               String goodWith,
               Boolean isSpayed,
               Integer updatedBy) {
        this.id = id;
        this.name = name;
        this.speciesId = speciesId;
        this.breedId = breedId;
        this.age = age;
        this.genre = genre;
        this.weight = weight;
        this.description = description;
        this.size = size;
        this.color = color;
        this.healthStatus = healthStatus;
        this.characteristics = characteristics;
        this.adoptionStatus = adoptionStatus;
        this.shelterId = shelterId;
        this.arrivalDate = arrivalDate;
        this.goodWith = goodWith;
        this.isSpayed = isSpayed;
        this.updatedBy = updatedBy;
    }
}
