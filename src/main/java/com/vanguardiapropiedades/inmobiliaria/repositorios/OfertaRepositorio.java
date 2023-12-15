package com.vanguardiapropiedades.inmobiliaria.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vanguardiapropiedades.inmobiliaria.entidades.OfertaEntidad;

@Repository
public interface OfertaRepositorio extends JpaRepository<OfertaEntidad, String> {

    // ? Traer ofertas de un ente
    @Query("SELECT o FROM OfertaEntidad o WHERE o.propiedad.usuario.id = :id")
    public List<OfertaEntidad> buscarPorPropiedad(@Param("id") String id);

}
