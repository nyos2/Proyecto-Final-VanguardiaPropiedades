package com.vanguardiapropiedades.inmobiliaria.entidades;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.GenericGenerator;

import com.vanguardiapropiedades.inmobiliaria.Enums.Cita;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

    private String nota;  // para ingresar un texto (datos de contacto) en el pedido de reunion

    @Temporal(TemporalType.DATE)
    private LocalDate fecha;

    @Temporal(TemporalType.TIME)
    private LocalTime hora; // ver que es mejor como alternativa para fecha y hora

    @Enumerated(EnumType.STRING)
    private Cita estado; // el que recibe la cita la Acepta o Rechaza

    

}
