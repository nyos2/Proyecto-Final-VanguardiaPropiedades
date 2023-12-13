package com.vanguardiapropiedades.inmobiliaria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanguardiapropiedades.inmobiliaria.entidades.CitaEntidad;

@Repository
public interface CitaRepositorio extends JpaRepository<CitaEntidad,String> {
    

}
