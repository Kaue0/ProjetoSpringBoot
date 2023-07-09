package com.example.api.controller;

import com.example.api.domain.post.dto.ListPostData;
import com.example.api.domain.post.Post;
import com.example.api.domain.post.dto.CreatePostData;
import com.example.api.domain.post.dto.PostFullData;
import com.example.api.domain.post.dto.UpdatePostData;
import com.example.api.domain.user.User;
import com.example.api.repositories.PostRepository;
import com.example.api.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("posts")
public class PostController {

    @Autowired
    private PostRepository repository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity createPost(@RequestBody @Valid CreatePostData data, UriComponentsBuilder uriBuilder) {
        User userOwner = GetUserLogged();
        System.out.println(userOwner);
        var post = new Post(data, userOwner);
        repository.save(post);

        var uri = uriBuilder.path("/posts/{id}").buildAndExpand(post.getPost_id()).toUri();

        return ResponseEntity.created(uri).body(new PostFullData(post));
    }

    @GetMapping
    public ResponseEntity<Page<ListPostData>> listPosts(Pageable pageable) {
        var result = repository.findAllByDeletedFalse(pageable).map(ListPostData::new);

        return ResponseEntity.ok(result);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdatePostData data) {
        var post = repository.getReferenceById(data.postid());
        post.updateData(data);

        return ResponseEntity.ok(new PostFullData(post));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var post = repository.getReferenceById(id);
        post.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity display(@PathVariable Long id) {
        var post = repository.getReferenceById(id);

        return ResponseEntity.ok(new PostFullData(post));
    }

    private User GetUserLogged() {
        User userAt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.getReferenceById(userAt.getUser_id());
    }
}
