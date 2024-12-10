package io.devandre.furtmates.pets.entity;

import io.devandre.furtmates.shared.persistence.AbstractAuditingEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "furtmates_schema", name = "breeds")
@Builder
public class Breed extends AbstractAuditingEntity {

    @Id
    private Long id;

    private String name;

    private Integer speciesId;

    private Integer updatedBy;

    public Breed() {}

    public Breed(Long id, String name, Integer speciesId, Integer updatedBy) {
        this.id = id;
        this.name = name;
        this.speciesId = speciesId;
        this.updatedBy = updatedBy;
    }
}
