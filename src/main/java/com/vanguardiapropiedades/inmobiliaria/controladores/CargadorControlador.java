package com.vanguardiapropiedades.inmobiliaria.controladores;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import com.vanguardiapropiedades.inmobiliaria.enums.Rol;
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
        return "Cargado Usuarios aleatorios";
    }

    @GetMapping("/cargar/roles")
    public String cargarUsuarios() {
        // Crear un usuario admin
        UsuarioEntidad admin = new UsuarioEntidad();
        admin.setNombre("Admin");
        admin.setEmail("admin@gmail.com");
        admin.setPassword(new BCryptPasswordEncoder().encode("asd"));
        admin.setRol(Rol.ADMIN);
        admin.setDni("00000000");
        usuarioRepositorio.save(admin);

        // Crear un usuario cliente
        UsuarioEntidad cliente = new UsuarioEntidad();
        cliente.setNombre("Cliente");
        cliente.setEmail("cliente@gmail.com");
        cliente.setPassword(new BCryptPasswordEncoder().encode("asd"));
        cliente.setRol(Rol.CLIENT);
        cliente.setDni("00000001");
        usuarioRepositorio.save(cliente);

        // Crear un usuario ente
        UsuarioEntidad ente = new UsuarioEntidad();
        ente.setNombre("Ente");
        ente.setEmail("ente@gmail.com");
        ente.setPassword(new BCryptPasswordEncoder().encode("asd"));
        ente.setRol(Rol.ENTE);
        ente.setDni("00000002");
        usuarioRepositorio.save(ente);

        return "Cargado Admin, Cliente y Ente";
    }
}
