package com.example.api.domain.post;

import com.example.api.domain.post.dto.CreatePostData;
import com.example.api.domain.post.dto.UpdatePostData;
import com.example.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "Posts")
@Entity(name = "post")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "post_id")
public class Post {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;
    private String title;
    private String description;
    private String photoLink;
    private String videoLink;
    private Boolean privated;
    private Boolean deleted;
    private Date createdAt;
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Post(CreatePostData data, User user) {
        this.title = data.title();
        this.description = data.description();
        this.photoLink = data.photoLink();
        this.videoLink = data.videoLink();
        this.privated = false;
        this.deleted = false;
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.user = user;

        this.user.getPosts().add(this);
    }

    public void setPost_id(Long postId) {
        this.post_id = postId;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void updateData(UpdatePostData data) {
        if (data.title() != null) {
            this.title = data.title();
        }
        if (data.description() != null) {
            this.description = data.description();
        }
        if (data.photoLink() != null) {
            this.photoLink = data.photoLink();
        }
        if (data.videoLink() != null) {
            this.videoLink = data.videoLink();
        }
        this.updatedAt = new Date();
    }

    public void delete() {
        this.deleted = true;
    }

    public void changeVisualization() {
        this.privated = !this.privated;

        this.updatedAt = new Date();
    }
}
