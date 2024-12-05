package io.devandre.furtmates.users.entity;

import io.devandre.furtmates.shared.persistence.AbstractAuditingEntity;
import io.devandre.furtmates.users.entity.enums.UserGenre;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@Table(schema = "furtmates_schema", name = "users")
public class User extends AbstractAuditingEntity {

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
    private String documentNumber;
    private Integer roleId;
    private String avatarUrl;
    private Integer age;
    private UserGenre genre;
    private Boolean isEnabled;
    private Boolean isAdopter;
    private String bio;

    public User() {}

    public User(UUID publicId, String firstName, String lastName, String username, String email, String passwordHash, String phoneNumber, String address, String documentNumber, Integer roleId, String avatarUrl, Integer age, UserGenre genre, Boolean isEnabled, Boolean isAdopter, String bio) {
        this.publicId = publicId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.documentNumber = documentNumber;
        this.roleId = roleId;
        this.avatarUrl = avatarUrl;
        this.age = age;
        this.genre = genre;
        this.isEnabled = isEnabled;
        this.isAdopter = isAdopter;
        this.bio = bio;
    }
}
