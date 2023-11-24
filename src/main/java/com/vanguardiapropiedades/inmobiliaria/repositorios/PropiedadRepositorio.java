package com.vanguardiapropiedades.inmobiliaria.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanguardiapropiedades.inmobiliaria.Entities.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, String> {
 public Property findByTipo(String tipo);
}
