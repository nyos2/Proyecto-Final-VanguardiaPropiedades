package com.vanguardiapropiedades.inmobiliaria.entidades;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;

import com.vanguardiapropiedades.inmobiliaria.Enums.Oferta;

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
public class OfertaEntidad {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2") // Estrategia alternativa
    private String id;

    private Integer valorOferta;

    @ManyToOne
    private PropiedadEntidad propiedad;

    @ManyToOne
    private UsuarioEntidad usuario;

    @Enumerated(EnumType.STRING)
    private Oferta estado; // Estado de la oferta (Aceptada, Rechazada, Pendiente)

    @Temporal(TemporalType.DATE)
    private LocalDate fecha;
}
