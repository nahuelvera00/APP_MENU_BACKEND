package com.nahuel.apirest.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@ToString
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "business")
public class Business {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name="user_id", nullable = false)
    private User user;


    @Column(name = "name")
    private String name;

    public Business(User user, String name) {
        this.user = user;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Business business = (Business) o;
        return id != null && Objects.equals(id, business.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
