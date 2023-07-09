package com.example.api.domain.user.dto;

import com.example.api.domain.user.User;

import java.util.Date;

public record UserFullData(Long userid, String name, String username, String phone, String email, String password, String profileLink, Date createdAt, Date updatedAt) {

    public UserFullData(User user) {
        this(user.getUser_id(), user.getName(), user.getUsername(), user.getPhone(), user.getEmail(), user.getPassword(), user.getProfileLink(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
