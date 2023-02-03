package com.nahuel.apirest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/business")
public class BusinessController {


    @GetMapping("/hello")
    public String hello(@RequestHeader(value="Authorization") String token) {
        return token;
    }

}
