package io.devandre.furtmates.pets.entity;

import io.devandre.furtmates.shared.persistence.AbstractAuditingEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "furtmates_schema", name = "species")
@Builder
public class Specie extends AbstractAuditingEntity {

    @Id
    private Long id;

    private String name;

    private Integer updatedBy;

    public Specie() {}

    public Specie(Long id, String name, Integer updatedBy) {
        this.id = id;
        this.name = name;
        this.updatedBy = updatedBy;
    }
}
