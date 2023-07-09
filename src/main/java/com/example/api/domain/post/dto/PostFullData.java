package com.example.api.domain.post.dto;

import com.example.api.domain.post.Post;

import java.util.Date;

public record PostFullData(Long postid, Long userid, String username, String title, String description, String photoLink, String videoLink, Date createdAt, Date updatedAt) {

    public PostFullData(Post post) {
        this(post.getPost_id(), post.getUser().getUser_id(), post.getUser().getUsername(), post.getTitle(), post.getDescription(), post.getPhotoLink(), post.getVideoLink(), post.getCreatedAt(), post.getUpdatedAt());
    }
}
