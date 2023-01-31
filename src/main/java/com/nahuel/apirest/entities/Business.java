package com.nahuel.apirest.entities;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "business")
@Getter
@Setter
@Builder
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_user")
    private Long id_user;

    @Column(name = "name")
    private String name;

    public Business() {

    }

    public Business(Long id, Long id_user, String name) {
        this.id = id;
        this.id_user = id_user;
        this.name = name;
    }

}
