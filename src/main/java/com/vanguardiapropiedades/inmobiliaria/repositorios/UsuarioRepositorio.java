package com.vanguardiapropiedades.inmobiliaria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanguardiapropiedades.inmobiliaria.entidades.UsuarioEntidad;

@Repository
public interface UsuarioRepositorio extends JpaRepository<UsuarioEntidad, String> {
    public UsuarioEntidad findByEmail(String email);

    public boolean existsByEmail(String email);
}
