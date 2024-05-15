package com.example.entity;

import com.example.enums.ERole;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(STRING)
    private ERole role;

    public static final RoleEntity ADMIN = RoleEntity.builder().role(ERole.ADMIN).build();
    public static final RoleEntity USER = RoleEntity.builder().role(ERole.USER).build();
    public static final RoleEntity INVITED = RoleEntity.builder().role(ERole.INVITED).build();

    @PostConstruct
    public void init() {
        ADMIN.setId(1L);
        USER.setId(2L);
        INVITED.setId(3L);
    }

    @PrePersist
    public void prePersist() {
        if (role == null) role = ERole.USER;
    }

    private RoleEntity(ERole role) {
        this.role = role;
    }
}

/*
Esta clase se crea para representar un rol de usuario en la base de datos.

@Data de Lombok es para generar los getters y setters.
@AllArgsConstructor de Lombok es para generar un constructor con todos los parámetros.
@NoArgsConstructor de Lombok es para generar un constructor sin parámetros.
@Builder de Lombok es para generar un constructor con todos los parámetros a partir de un builder().

@Id de JPA para indicar que el campo es la clave primaria.
@GeneratedValue de JPA para indicar que el campo es autoincremental.
@Enumerated de JPA para indicar que el campo es de tipo enumerado.
@Enumerated(STRING) de JPA para indicar que el campo es de tipo enumerado de tipo String.
*/