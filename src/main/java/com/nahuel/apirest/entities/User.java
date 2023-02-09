package com.nahuel.apirest.entities;

import com.nahuel.apirest.models.UserBasicDTO;
import com.nahuel.apirest.models.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity //Define esta clase como una entidad para poderla crear en la DB
@Getter //Crea de manera interna los metodos Getter
@Setter //Crea de manera interna los metodos Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users") //DEFINE el nombre de la TABLA
public class  User implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1,
            initialValue = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    ) //Genera una ID autoincrementable segun la ID mas grande en la DB
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

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private Set<Business> businesses = new HashSet<>();


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
}
