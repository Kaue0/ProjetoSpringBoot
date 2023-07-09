package com.example.api.controller;

import com.example.api.domain.user.User;
import com.example.api.domain.user.dto.LoginData;
import com.example.api.infra.security.TokenService;
import com.example.api.infra.security.tokenJWTData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity logIn(@RequestBody @Valid LoginData data) {
        var token = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = manager.authenticate(token);

        var tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new tokenJWTData(tokenJWT));
    }
}
