package com.example.api.domain.user;

import com.example.api.domain.post.Post;
import com.example.api.domain.user.dto.RegisterUserData;
import com.example.api.domain.user.dto.UpdateUserData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name = "Usuarios")
@Entity(name = "usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "user_id")
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String phone;
    private String email;
    private String password;
    private String profileLink;
    private Boolean deleted;
    private Date createdAt;
    private Date updatedAt;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

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
        this.posts = new ArrayList<>();
    }

    public void setUser_id(Long userId) {
        this.id = userId;
    }

    public Long getUser_id() {
        return id;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}

