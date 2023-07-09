package com.example.api.domain.post.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UpdatePostData(
        @NotNull
        Long postid,
        String title,
        String description,
        String photoLink,
        String videoLink,
        Date updatedAt) {
}
