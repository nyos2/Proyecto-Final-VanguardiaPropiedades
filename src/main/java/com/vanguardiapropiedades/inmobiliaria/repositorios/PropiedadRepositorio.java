package com.vanguardiapropiedades.inmobiliaria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanguardiapropiedades.inmobiliaria.entidades.PropiedadEntidad;

@Repository
public interface PropiedadRepositorio extends JpaRepository<PropiedadEntidad, String> {
    public PropiedadEntidad findByTipo(String Tipo);
}
