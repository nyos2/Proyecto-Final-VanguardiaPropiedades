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
    @GenericGenerator(name = "uuid", strategy = "uuid2") 
    private String id;

    @ManyToOne
    private UsuarioEntidad cliente; // cliente logueado pidiendo una reunion

    @ManyToOne
    private UsuarioEntidad ente; // ente o dueño de la propiedad

    @ManyToOne
    private PropiedadEntidad propiedad; // Sera necesario ???

    private String nota; // para un texto o nota agregado en el pedido de reunion

    private String fechahora; // ver que es mejor como alternativa para fecha y hora
    
    private Boolean acepto; // el que recibe la cita cambia este estado a true (aceptado)

    // Agregar lo relativo al calendario de la cita =>  fecha y hora. Ver que conviene hacer
    // private Date fechacita;

    // private Time  horacita;

}
