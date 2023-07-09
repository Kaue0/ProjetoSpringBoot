package com.example.api.domain.user.dto;

import jakarta.validation.constraints.*;

public record RegisterUserData(
        @NotBlank(message = "Insira um nome")
        String name,

        @NotBlank(message = "Insira um username")
        @Size(min = 4, max = 50)
        String username,
        String phone,
        @NotBlank(message = "Email é obrigatório")
        @Email
        String email,
        @NotBlank(message = "Sua senha não pode ser vazia")
        @Size(min = 5, max = 255)
        String password,
        String profileLink) {
}
