package com.vanguardiapropiedades.inmobiliaria.entidades;

import java.util.Date;

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
    private PropiedadEntidad propiedad; 

    private String nota; // para ingresar un texto (datos de contacto) en el pedido de reunion

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    
    @Enumerated(EnumType.STRING)
    private Cita estado; // el que recibe la cita la Acepta o Rechaza

}
