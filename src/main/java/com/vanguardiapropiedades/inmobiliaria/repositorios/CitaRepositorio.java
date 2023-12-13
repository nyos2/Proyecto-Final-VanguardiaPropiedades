package com.vanguardiapropiedades.inmobiliaria.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vanguardiapropiedades.inmobiliaria.entidades.CitaEntidad;
import com.vanguardiapropiedades.inmobiliaria.entidades.OfertaEntidad;

@Repository
public interface CitaRepositorio extends JpaRepository<CitaEntidad, String> {

    // ? Traer ofertas de un ente
    @Query("SELECT c FROM CitaEntidad c WHERE c.ente.id = :id")
    public List<CitaEntidad> buscarPorEnte(@Param("id") String id);
}
