package com.vanguardiapropiedades.inmobiliaria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/nosotros")
    public String nosotros() {
        return "sobre_nosotros.html";
    }
}
