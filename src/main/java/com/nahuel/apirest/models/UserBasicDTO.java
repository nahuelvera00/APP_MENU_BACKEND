package com.nahuel.apirest.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBasicDTO {

    //Atributes
    private Long id;
    private String name;
    private String surname;
    private int cellphone;
    private String email;

    public UserBasicDTO(){
        
    }

    public UserBasicDTO(Long id, String name, String surname, int cellphone, String email) {
        this.id = id;
        this.name = name;
        this. surname = surname;
        this.cellphone = cellphone;
        this.email = email;
    }

}
