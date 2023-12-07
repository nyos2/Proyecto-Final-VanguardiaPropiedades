package com.vanguardiapropiedades.inmobiliaria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanguardiapropiedades.inmobiliaria.entidades.OfertaEntidad;

@Repository
public interface OfertaRepositorio extends JpaRepository<OfertaEntidad, String> {

}
