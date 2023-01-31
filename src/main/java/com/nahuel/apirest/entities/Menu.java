package com.nahuel.apirest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "menu")
@Getter
@Setter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(name = "id_business")
    private Long id_business;

    @Column(name = "name")
    private String name;

    public Menu() {

    }

    public Menu(Long id, Long id_business, String name) {
        this.id = id;
        this.id_business = id_business;
        this.name = name;
    }

}
