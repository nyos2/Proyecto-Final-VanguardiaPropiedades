package com.vanguardiapropiedades.inmobiliaria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanguardiapropiedades.inmobiliaria.entidades.ImagenEntidad;

@Repository
public interface ImagenRepositorio extends JpaRepository<ImagenEntidad, String> {

}
