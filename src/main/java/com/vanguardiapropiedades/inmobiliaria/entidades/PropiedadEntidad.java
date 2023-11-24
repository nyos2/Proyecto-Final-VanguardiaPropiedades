package com.vanguardiapropiedades.inmobiliaria.entidades;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.vanguardiapropiedades.inmobiliaria.Enums.Tipo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class PropiedadEntidad {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Integer precio;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @OneToOne
    private UsuarioEntidad usuario;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImagenEntidad> imagenes;
    private Boolean estado;
}
