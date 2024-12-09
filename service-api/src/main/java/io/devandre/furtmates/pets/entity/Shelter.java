package io.devandre.furtmates.pets.entity;

import io.devandre.furtmates.shared.persistence.AbstractAuditingEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "furtmates_schema", name = "shelters")
@Builder
public class Shelter extends AbstractAuditingEntity {

    @Id
    private Long id;

    private String name;

    private String phoneNumber;

    private String address;

    private String email;

    private Integer updatedBy;

    public Shelter() {}

    public Shelter(Long id, String name, String phoneNumber, String address, String email, Integer updatedBy) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.updatedBy = updatedBy;
    }
}
