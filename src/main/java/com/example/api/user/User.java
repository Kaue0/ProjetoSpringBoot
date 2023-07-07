package com.example.api.user;

import com.example.api.post.Post;
import com.example.api.user.dto.RegisterUserData;
import com.example.api.user.dto.UpdateUserData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Table(name = "Usuarios")
@Entity(name = "usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "user_id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String name;
    private String username;
    private String phone;
    private String email;
    private String password;
    private String profileLink;
    private Boolean deleted;
    private Date createdAt;
    private Date updatedAt;

    //@OneToMany(mappedBy = "user")
    //private List<Post> posts;

    public User(RegisterUserData data) {
        this.name = data.name();
        this.username = data.username();
        this.phone = data.phone();
        this.email = data.email();
        this.password = data.password();
        this.profileLink = data.profileLink();
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.deleted = false;
    }

    public void setUser_id(Long userId) {
        this.user_id = userId;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void updateData(UpdateUserData data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.username() != null) {
            this.username = data.username();
        }
        if (data.phone() != null) {
            this.phone = data.phone();
        }
        if (data.password() != null) {
            this.password = data.password();
        }
        if (data.profileLink() != null) {
            this.profileLink = data.profileLink();
        }
        if (data.email() != null) {
            this.email = data.email();
        }
        this.updatedAt = new Date();


    }

    public void delete() {
        this.deleted = true;
    }
}

