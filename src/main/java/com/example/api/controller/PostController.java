package com.example.api.controller;

import com.example.api.post.dto.ListPostData;
import com.example.api.post.Post;
import com.example.api.post.dto.CriarPostData;
import com.example.api.post.dto.UpdatePostData;
import com.example.api.repositories.PostRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("posts")
public class PostController {

    @Autowired
    private PostRepository repository;
    @PostMapping
    public void createPost(@RequestBody CriarPostData data) {
        repository.save(new Post(data));
    }

    @GetMapping
    public Page<ListPostData> listPosts(Pageable pageable) {
        return repository.findAllByDeletedFalse(pageable).map(ListPostData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdatePostData data) {
        var user = repository.getReferenceById(data.postid());
        user.updateData(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var post = repository.getReferenceById(id);
        post.delete();
    }
}
