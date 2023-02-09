package com.nahuel.apirest.controllers;

import com.nahuel.apirest.models.LoginResponseDTO;
import com.nahuel.apirest.models.LoginRequestDTO;
import com.nahuel.apirest.models.UserDTO;
import com.nahuel.apirest.services.AuthenticationServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationServices authServices;

    public AuthController(AuthenticationServices authServices) {
        this.authServices = authServices;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO userData) {

        return authServices.login(userData);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody UserDTO userData) {
        return authServices.register(userData);
    }

    @PostMapping("/profile")
    public ResponseEntity<LoginResponseDTO> profile(@RequestHeader(value="Authorization") String token) {
        return authServices.profile(token);
    }

}
