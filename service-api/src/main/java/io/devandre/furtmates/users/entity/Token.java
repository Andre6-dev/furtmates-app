package io.devandre.furtmates.users.entity;

import io.devandre.furtmates.shared.persistence.AbstractAuditingEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(schema = "furtmates_schema", name = "tokens")
@Builder
public class Token extends AbstractAuditingEntity {

    @Id
    private Long id;
    private Long userId;
    private String tokenType;
    private String token;
    private boolean revoked;
    private boolean expired;
    private LocalDateTime expirationDate;

    public Token() {}

    public Token(Long id,
                 Long userId,
                 String tokenType,
                 String token,
                 boolean revoked,
                 boolean expired,
                 LocalDateTime expirationDate) {
        this.id = id;
        this.userId = userId;
        this.tokenType = tokenType;
        this.token = token;
        this.revoked = revoked;
        this.expired = expired;
        this.expirationDate = expirationDate;
    }

    public Token(Long userId, String tokenType, String token, boolean revoked, boolean expired, LocalDateTime expirationDate) {
        this.userId = userId;
        this.tokenType = tokenType;
        this.token = token;
        this.revoked = revoked;
        this.expired = expired;
        this.expirationDate = expirationDate;
    }
}
