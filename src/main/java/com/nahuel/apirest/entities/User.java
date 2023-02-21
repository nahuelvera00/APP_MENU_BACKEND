package com.nahuel.apirest.entities;

import com.nahuel.apirest.models.UserBasicDTO;
import com.nahuel.apirest.models.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@ToString
@Getter //Crea de manera interna los metodos Getter
@Setter //Crea de manera interna los metodos Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity //Define esta clase como una entidad para poderla crear en la DB
@Table(name = "user_auth") //DEFINE el nombre de la TABLA
public class  User implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name = "cellphone")
    private int cellphone;

    @Column(name = "email")
    private String email;

    @Column(name="password")
    private String password;

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Business> businesses;


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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }



    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
