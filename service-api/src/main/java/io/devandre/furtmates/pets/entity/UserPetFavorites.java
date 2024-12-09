package io.devandre.furtmates.pets.entity;

import io.devandre.furtmates.shared.persistence.AbstractAuditingEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "furtmates_schema", name = "users_favorites")
@Builder
public class UserPetFavorites extends AbstractAuditingEntity {

    @Id
    private Long id;

    private Long userId;

    private Long petId;

    public UserPetFavorites() {}

    public UserPetFavorites(Long id, Long userId, Long petId) {
        this.id = id;
        this.userId = userId;
        this.petId = petId;
    }
}
