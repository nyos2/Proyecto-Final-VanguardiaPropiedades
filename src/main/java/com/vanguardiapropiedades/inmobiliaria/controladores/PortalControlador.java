package com.vanguardiapropiedades.inmobiliaria.controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vanguardiapropiedades.inmobiliaria.entidades.UsuarioEntidad;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @GetMapping({ "/", "/index" }) // Me permite usar dos url sin duplicar el codigo
    public String home() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Credenciales incorrectas");
        }
        return "login.html";
    }

// Para probar inicialmente

     @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_ADMIN')")
    @GetMapping("/perfil")
    public String perfil(ModelMap modelo, HttpSession session) {
        
        UsuarioEntidad usuario = (UsuarioEntidad) session.getAttribute("usuariosession");
        
        modelo.put("usuario", usuario);
        
        return "/Usuario/usuario_perfil.html";
    }
}
