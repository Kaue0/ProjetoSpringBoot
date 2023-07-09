package com.example.api.domain.post.dto;

import com.example.api.domain.post.Post;

public record ListPostData(Long postid, String title, String description, String photoLink, String videoLink) {

    public ListPostData(Post post) {
        this(post.getPost_id(), post.getTitle(), post.getDescription(), post.getPhotoLink(), post.getVideoLink());
    }
}
