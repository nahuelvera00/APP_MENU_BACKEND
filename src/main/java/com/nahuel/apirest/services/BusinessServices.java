package com.nahuel.apirest.services;

import com.nahuel.apirest.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServices {

    private final BusinessRepository businessRepository;

    public BusinessServices(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    public void hello(String token) {
    }

}
