package io.devandre.furtmates.users.entity;

import io.devandre.furtmates.shared.persistence.AbstractAuditingEntity;
import io.devandre.furtmates.users.entity.enums.UserGenre;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Table("users")
public class User extends AbstractAuditingEntity<Long> {

    @Id
    private Long id;

    private UUID publicId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String passwordHash;
    private String phoneNumber;
    private String address;
    private Integer roleId;
    private String avatarUrl;
    private Integer age;
    private String job;
    private UserGenre genre;
    private Boolean isEnabled;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public User() {}

    public User(UUID publicId, String firstName, String lastName, String username, String email, String passwordHash, String phoneNumber, String address, Integer roleId, String avatarUrl, Integer age, String job, UserGenre genre, Boolean isEnabled, Timestamp createdAt, Timestamp updatedAt) {
        this.publicId = publicId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.roleId = roleId;
        this.avatarUrl = avatarUrl;
        this.age = age;
        this.job = job;
        this.genre = genre;
        this.isEnabled = isEnabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}