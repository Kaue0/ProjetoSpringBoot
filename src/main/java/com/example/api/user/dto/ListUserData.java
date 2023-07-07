package com.example.api.user.dto;

import com.example.api.user.User;

public record ListUserData(Long userid, String name, String username, String profileLink, String email) {

    public ListUserData(User user) {
        this(user.getUser_id(), user.getName(), user.getUsername(), user.getProfileLink(), user.getEmail());
    }
}
