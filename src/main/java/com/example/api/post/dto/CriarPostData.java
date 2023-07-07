package com.example.api.post.dto;

import jakarta.validation.constraints.NotBlank;

public record CriarPostData(
        @NotBlank(message = "Insira um titulo")
        String title,
        @NotBlank(message = "Insira uma descrição")
        String description,
        String photoLink,
        String videoLink) {

}
