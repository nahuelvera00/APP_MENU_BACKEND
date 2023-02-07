package com.nahuel.apirest.controllers;

import com.nahuel.apirest.auth.JwtResponse;
import com.nahuel.apirest.models.LoginDTO;
import com.nahuel.apirest.models.UserDTO;
import com.nahuel.apirest.services.AuthenticationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationServices authServices;

    public AuthController(AuthenticationServices authServices) {
        this.authServices = authServices;
    }


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDTO userData) {

        return authServices.login(userData);
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody UserDTO userData) {
        return authServices.register(userData);
    }

}
