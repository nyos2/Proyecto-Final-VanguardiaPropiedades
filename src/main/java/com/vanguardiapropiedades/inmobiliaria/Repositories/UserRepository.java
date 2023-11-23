package com.vanguardiapropiedades.inmobiliaria.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanguardiapropiedades.inmobiliaria.Entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    public UserEntity findByEmail(String email);

    public boolean existsByEmail(String email);
}
