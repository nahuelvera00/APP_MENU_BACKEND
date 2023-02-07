package com.nahuel.apirest.controllers;

import com.nahuel.apirest.services.BusinessServices;
import com.nahuel.apirest.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/business")
public class BusinessController {

    public BusinessServices businessServices;
    private final JwtService jwtService;


    @GetMapping("/hello")
    public String hello(@RequestHeader(value="Authorization") String token) {

        return jwtService.extractUsername(token.replace("Bearer ", ""));
    }

}
