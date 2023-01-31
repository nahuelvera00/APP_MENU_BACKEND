package com.nahuel.apirest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserDTO {
    //atributos
    private Long id;
    private String name;
    private String surname;
    private int cellphone;
    private String email;
    private String password;

    //constructor
    public UserDTO() {}

    public UserDTO(Long id, String name,String surname, int cellphone, String email, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.cellphone = cellphone;
        this.email = email;
        this.password = password;
    }

    //metodos
}
