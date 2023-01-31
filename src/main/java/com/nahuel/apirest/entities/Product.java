package com.nahuel.apirest.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_menu")
    private Long id_menu;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    public Product() {

    }

    public Product(Long id, Long id_menu ,String name, String description, Double price) {
        this.id = id;
        this.id_menu = id_menu;
        this.name = name;
        this.description = description;
        this.price = price;
    }


}
