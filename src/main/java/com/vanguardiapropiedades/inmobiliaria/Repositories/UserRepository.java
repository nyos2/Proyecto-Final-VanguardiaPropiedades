package com.vanguardiapropiedades.inmobiliaria.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanguardiapropiedades.inmobiliaria.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public User findByEmail(String email);

    public boolean existsByEmail(String email);
}
