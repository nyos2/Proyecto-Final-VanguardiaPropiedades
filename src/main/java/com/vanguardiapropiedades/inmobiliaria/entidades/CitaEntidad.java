package com.vanguardiapropiedades.inmobiliaria.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CitaEntidad {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2") // Estrategia alternativa
    private String id;

    private UsuarioEntidad cliente;

    private UsuarioEntidad ente;

    private String detalle;

    private Boolean acepto;

    // private Date citainicio;

    // private Date citafin;

}
