package com.vanguardiapropiedades.inmobiliaria.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanguardiapropiedades.inmobiliaria.Entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

}
