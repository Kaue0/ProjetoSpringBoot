package com.example.api.repositories;

import com.example.api.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByDeletedFalse(Pageable pageable);

    UserDetails findByUsername(String username);
}
