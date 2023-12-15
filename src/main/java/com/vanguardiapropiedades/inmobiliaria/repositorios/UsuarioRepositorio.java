package com.vanguardiapropiedades.inmobiliaria.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vanguardiapropiedades.inmobiliaria.entidades.UsuarioEntidad;

@Repository
public interface UsuarioRepositorio extends JpaRepository<UsuarioEntidad, String> {
    public UsuarioEntidad findByEmail(String email);

    public boolean existsByEmail(String email);

    public Page<UsuarioEntidad> findByDni(String dni, Pageable pageable);

    @Query("SELECT u FROM UsuarioEntidad u WHERE u.email = :email")
    public Page<UsuarioEntidad> buscarporEmail(@Param("email") String email, Pageable pageable);
}
