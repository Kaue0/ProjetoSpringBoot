package com.example.api.controller;

import com.example.api.user.dto.ListUserData;
import com.example.api.user.dto.RegisterUserData;
import com.example.api.user.dto.UpdateUserData;
import com.example.api.user.User;
import com.example.api.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {


    @Autowired
    private UserRepository repository;
    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid RegisterUserData data) {
        repository.save(new User(data));
        //Na criação deve conter algumas regras de validação como: verificar se o
        //username, email e telefone já são existentes e também se o email é válido.
        //Obs: Os campos nome, username, email e password não devem ser nulos

    }

    @GetMapping
    public Page<ListUserData> listUsers(Pageable pageable) {
        return repository.findAllByDeletedFalse(pageable).map(ListUserData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateUserData data) {
        var user = repository.getReferenceById(data.userid());
        user.updateData(data);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var user = repository.getReferenceById(id);
        user.delete();
    }
}
