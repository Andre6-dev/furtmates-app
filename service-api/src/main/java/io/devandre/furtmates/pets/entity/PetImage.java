package io.devandre.furtmates.pets.entity;

import io.devandre.furtmates.shared.persistence.AbstractAuditingEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "furtmates_schema", name = "pet_images")
@Builder
public class PetImage extends AbstractAuditingEntity {

    @Id
    private Long id;

    private Long petId;
    private String imageUrl;

    public PetImage() {}

    public PetImage(Long id, Long petId, String imageUrl) {
        this.id = id;
        this.petId = petId;
        this.imageUrl = imageUrl;
    }
}
