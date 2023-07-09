package com.example.api.controller;

import com.example.api.domain.user.dto.ListUserData;
import com.example.api.domain.user.dto.RegisterUserData;
import com.example.api.domain.user.dto.UpdateUserData;
import com.example.api.domain.user.User;
import com.example.api.repositories.UserRepository;
import com.example.api.domain.user.dto.UserFullData;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegisterUserData data, UriComponentsBuilder uriBuilder) {
        var user = new User(data);
        repository.save(user);

        var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getUser_id()).toUri();

        return ResponseEntity.created(uri).body(new UserFullData(user));

    }

    @GetMapping
    public ResponseEntity<Page<ListUserData>> listUsers(Pageable pageable) {
        var result = repository.findAllByDeletedFalse(pageable).map(ListUserData::new);

        return ResponseEntity.ok(result);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateUserData data) {
        var user = repository.getReferenceById(data.userid());
        user.updateData(data);

        return ResponseEntity.ok(new UserFullData(user));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var user = repository.getReferenceById(id);
        user.delete();

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity display(@PathVariable Long id) {
        var user = repository.getReferenceById(id);

        return ResponseEntity.ok(new UserFullData(user));
    }
}
