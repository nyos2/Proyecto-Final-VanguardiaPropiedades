package com.vanguardiapropiedades.inmobiliaria.entidades;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.vanguardiapropiedades.inmobiliaria.Enums.Rol;
//import com.vanguardiapropiedades.inmobiliaria.entidades.PropiedadEntidad;
//import com.vanguardiapropiedades.inmobiliaria.entidades.ImagenEntidad;
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
public class UsuarioEntidad {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String dni;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @OneToMany
    private List<PropiedadEntidad> propiedades;
    @OneToOne
    private ImagenEntidad imagen;
}