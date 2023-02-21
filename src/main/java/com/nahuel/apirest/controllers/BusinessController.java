package com.nahuel.apirest.controllers;

import com.nahuel.apirest.entities.Business;
import com.nahuel.apirest.models.BusinessDTO;
import com.nahuel.apirest.services.BusinessServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/business")
public class BusinessController {

    private final BusinessServices businessServices;


    @GetMapping("/hello")
    public String hello(@RequestHeader(value="Authorization") String token) {

        return businessServices.hello(token);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBusiness(@RequestHeader(value="Authorization") String token, @RequestBody BusinessDTO business) {
        return businessServices.create(token, business);
    }

    @GetMapping("/get")
    public ResponseEntity<List<BusinessDTO>> getBusiness(@RequestHeader(value="Authorization") String token){
        return businessServices.getBusiness(token);
    }

}
