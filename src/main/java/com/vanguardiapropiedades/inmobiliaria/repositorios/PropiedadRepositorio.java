package com.vanguardiapropiedades.inmobiliaria.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanguardiapropiedades.inmobiliaria.entidades.PropiedadEntidad;

@Repository
public interface PropiedadRepositorio extends JpaRepository<PropiedadEntidad, String> {

    // ? Listar solo con estado en true
    public Page<PropiedadEntidad> findByEstadoTrue(Pageable pageable);

}
