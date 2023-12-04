package com.vanguardiapropiedades.inmobiliaria.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class CitaEntidad {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2") // Estrategia alternativa
    private String id;

    @ManyToOne
    private UsuarioEntidad cliente; // cliente logueado pidiendo una reunion

    @ManyToOne
    private UsuarioEntidad ente; // ente o dueño de la propiedad

    private String nota; // para un texto o nota agregado en el pedido de reunion

    private String fecha; // ver que es mejor como alternativa para fecha y hora
    private String hora;
    // private Boolean acepto; // el que acepta cambia este estado a true

    // Agregar lo relativo al calendario de la cita =>  fecha y hora. Ver que conviene hacer
    // private Date fechacita;

    // private Time  horacita;

}
