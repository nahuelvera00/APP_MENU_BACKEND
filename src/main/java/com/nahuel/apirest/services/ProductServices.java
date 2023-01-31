package com.nahuel.apirest.services;

import com.nahuel.apirest.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServices {

    private final ProductRepository productRepository;

    public ProductServices(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


}
