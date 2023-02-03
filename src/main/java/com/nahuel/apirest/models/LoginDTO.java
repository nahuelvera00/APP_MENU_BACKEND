package com.nahuel.apirest.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class LoginDTO {

    private String email;
    private String password;

    public LoginDTO(){

    }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
