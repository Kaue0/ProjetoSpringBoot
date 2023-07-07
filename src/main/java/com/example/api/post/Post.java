package com.example.api.post;

import com.example.api.post.dto.CriarPostData;
import com.example.api.post.dto.UpdatePostData;
import com.example.api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "Posts")
@Entity(name = "post")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "post_id")
public class Post {

    //@ManyToOne(fetch = FetchType.LAZY)
    //private User user;

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


    public Post(CriarPostData data) {
        this.title = data.title();
        this.description = data.description();
        this.photoLink = data.photoLink();
        this.videoLink = data.videoLink();
        this.privated = false;
        this.deleted = false;
        this.createdAt = new Date();
        this.updatedAt = new Date();
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
}
