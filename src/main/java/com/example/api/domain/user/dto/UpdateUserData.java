package com.example.api.domain.user.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UpdateUserData(
        @NotNull
        Long userid,
        String name,
        String username,
        String phone,
        String password,
        String profileLink,
        String email,
        Date updatedAt) {

}
