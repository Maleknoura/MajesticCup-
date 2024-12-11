package org.wora.majesticcup.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class AppUser {
    @Id
    private String id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    private Role role;

    public @NotNull Role getRole() {
        return role;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public @NotBlank String getUsername() {
        return username;
    }
}