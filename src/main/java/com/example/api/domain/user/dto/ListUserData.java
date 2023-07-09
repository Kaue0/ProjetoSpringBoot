package com.example.api.domain.user.dto;

import com.example.api.domain.user.User;
import java.util.List;

public record ListUserData(Long userid, String name, String username, String profileLink, String email, String phone) {



    public ListUserData(User user) {
        this(user.getUser_id(), user.getName(), user.getUsername(), user.getProfileLink(), user.getEmail(), user.getPhone());
    }
}
