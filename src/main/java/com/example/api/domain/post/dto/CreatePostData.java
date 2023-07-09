package com.example.api.domain.post.dto;

import jakarta.validation.constraints.NotBlank;

public record CreatePostData(
        @NotBlank(message = "Insira um titulo")
        String title,
        @NotBlank(message = "Insira uma descrição")
        String description,
        String photoLink,
        String videoLink) {

}
