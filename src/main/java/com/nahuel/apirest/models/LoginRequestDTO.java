package com.nahuel.apirest.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class LoginRequestDTO {

    private String email;
    private String password;

    public LoginRequestDTO(){

    }

    public LoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
