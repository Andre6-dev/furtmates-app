package io.devandre.furtmates.users.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(schema = "furtmates_schema", name = "verification_code")
@Builder
public class ActivationCode {

    @Id
    private Long id;

    private Integer userId;

    private String key;

    private LocalDateTime expirationDate;

    public ActivationCode() {}

    public ActivationCode(Long id, Integer userId, String key, LocalDateTime expirationDate) {
        this.id = id;
        this.userId = userId;
        this.key = key;
        this.expirationDate = expirationDate;
    }

    public ActivationCode(Integer userId, String key, LocalDateTime expirationDate) {
        this.userId = userId;
        this.key = key;
        this.expirationDate = expirationDate;
    }
}
