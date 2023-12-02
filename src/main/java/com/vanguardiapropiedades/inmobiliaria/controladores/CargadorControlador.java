package com.vanguardiapropiedades.inmobiliaria.controladores;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vanguardiapropiedades.inmobiliaria.Enums.Rol;
import com.vanguardiapropiedades.inmobiliaria.entidades.UsuarioEntidad;
import com.vanguardiapropiedades.inmobiliaria.repositorios.UsuarioRepositorio;

@RestController
public class CargadorControlador {
    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/cargar/usuarios")
    public String usuarios() {
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            UsuarioEntidad u = new UsuarioEntidad();
            u.setNombre("Persona " + (i + 1));
            u.setEmail("persona" + (i + 1) + "@gmail.com");
            u.setPassword(new BCryptPasswordEncoder().encode("123456"));
            u.setRol(Rol.CLIENT);
            String dni = String.valueOf(random.nextInt(99999999));
            u.setDni(dni);
            usuarioRepositorio.save(u);
        }
        return "ok";
    }
}
