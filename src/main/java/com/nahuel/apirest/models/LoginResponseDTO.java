package com.nahuel.apirest.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class LoginResponseDTO {
    private Long id;
    private String name;
    private String surname;
    private int cellphone;
    private String email;
    private String token;

}
