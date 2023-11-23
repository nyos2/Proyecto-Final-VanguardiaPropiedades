package com.vanguardiapropiedades.inmobiliaria.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalController {

    @GetMapping("/")
    public String home() {
        return "index.html";
    }

    @GetMapping("/index")
    public String index() {
        return "index.html";
    }

    // @GetMapping("/registrar")
    // public String registrar(){
    // return "registrar.html";
    // }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Credenciales incorrectas");
        }
        return "login.html";
    }
}
