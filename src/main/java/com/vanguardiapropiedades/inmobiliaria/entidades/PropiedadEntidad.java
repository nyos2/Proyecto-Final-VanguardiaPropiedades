package com.vanguardiapropiedades.inmobiliaria.entidades;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.vanguardiapropiedades.inmobiliaria.Enums.Tipo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class PropiedadEntidad {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private int precio;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @Column(length = 1000)
    private String descripcion;
    private String direccion;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntidad usuario;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImagenEntidad> imagenes;
    private Boolean estado;
}
