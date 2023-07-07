package com.example.api.post.dto;

import com.example.api.post.Post;

public record ListPostData(Long postid, String title, String description, String photoLink, String videoLink) {

    public ListPostData(Post post) {
        this(post.getPost_id(), post.getTitle(), post.getDescription(), post.getPhotoLink(), post.getVideoLink());
    }
}
