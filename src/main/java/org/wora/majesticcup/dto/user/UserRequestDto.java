package org.wora.majesticcup.dto.user;


import jakarta.validation.constraints.NotBlank;
import org.wora.majesticcup.entity.Role;

public record UserRequestDto(
        @NotBlank String username,
        @NotBlank String password,
        Role role
) {
    public UserRequestDto {
        if (role == null) {
            role = Role.ROLE_USER;
        }
    }
}

