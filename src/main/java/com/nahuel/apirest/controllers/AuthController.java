package com.nahuel.apirest.controllers;

import com.nahuel.apirest.models.LoginDTO;
import com.nahuel.apirest.models.UserBasicDTO;
import com.nahuel.apirest.models.UserDTO;
import com.nahuel.apirest.services.AuthServices;
import com.nahuel.apirest.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthServices authServices;

    public AuthController(AuthServices authServices) {
        this.authServices = authServices;
    }

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO userData) {

        return authServices.login(userData);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userData) {
        return authServices.register(userData);
    }

}
