package com.vanguardiapropiedades.inmobiliaria.Entities;

import java.util.ArrayList;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import com.vanguardiapropiedades.inmobiliaria.Enums.Tipo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Property {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private int precio;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    private User usuario;
    private ArrayList<MultipartFile> imagen;
    private Boolean estado;

}
