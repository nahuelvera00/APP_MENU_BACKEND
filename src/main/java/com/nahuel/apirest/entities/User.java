package com.nahuel.apirest.entities;

import com.nahuel.apirest.models.UserBasicDTO;
import com.nahuel.apirest.models.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity //Define esta clase como una entidad para poderla crear en la DB
@Table(name = "users") //DEFINE el nombre de la TABLA
@Getter //Crea de manera interna los metodos Getter
@Setter //Crea de manera interna los metodos Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class  User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Genera una ID autoincrementable segun la ID mas grande en la DB
    @Column(name="id") //Setea el nombre de la Columna
    private Long id;

    private String name;
    private String surname;
    private int cellphone;
    private String email;
    private String password;


    public User(String name, String surname, int cellphone, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.cellphone = cellphone;
        this.email = email;
        this.password = password;
    }

    //Crea la conexion entre el entity y el servicio, devolviendo o realizando las acciones que necesitemos.
    //Retornando un objeto de la clase UserDTO
    public UserDTO toCoreModel() {

        return UserDTO
                .builder()
                .id(this.id)
                .name(this.name)
                .surname(this.surname)
                .cellphone(this.cellphone)
                .email(this.email)
                .password(this.password)
                .build();

    }

    public UserBasicDTO toCoreModelBasic() {

        return UserBasicDTO
                .builder()
                .id(this.id)
                .name(this.name)
                .surname(this.surname)
                .cellphone(this.cellphone)
                .email(this.email)
                .build();
    }
}
