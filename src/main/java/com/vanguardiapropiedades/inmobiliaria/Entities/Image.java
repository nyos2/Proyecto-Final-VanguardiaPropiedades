package com.vanguardiapropiedades.inmobiliaria.Entities;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2") // Estrategia alternativa
    private String id;
    private String mime;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property propiedad;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] contenido;
}
