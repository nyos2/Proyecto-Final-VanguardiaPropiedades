package com.vanguardiapropiedades.inmobiliaria.Entities;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.vanguardiapropiedades.inmobiliaria.Enums.Rol;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @OneToMany
    private List<Property> propiedades;
    @OneToOne
    private Image imagen;
}