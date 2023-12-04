package com.vanguardiapropiedades.inmobiliaria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vanguardiapropiedades.inmobiliaria.excepciones.MiException;
import com.vanguardiapropiedades.inmobiliaria.servicios.CitaServicio;

@Controller
@RequestMapping("/cita")
public class CitaControlador {
    
    private CitaServicio citaServicio;

    @GetMapping("/registrar")
    public String registrarCita() {
        return "Cita/cita_form.html";
    }

    @PostMapping("/registro") 

        public String registroCita(@RequestParam String idEnte, @RequestParam String idCliente, @RequestParam String nota, @RequestParam String fecha, @RequestParam String email,
            @RequestParam String hora, ModelMap modelo) throws MiException {
                // no me queda claro si tomar propiedad tambien, puede ser necesario
                try {
                    
                    citaServicio.crearCita(idEnte, idCliente, fecha, hora, nota);
                    modelo.put("exito", "la cita fue cargada correctamente");
                } catch (Exception e) {
                    
                    return "cita_form.html";
        }
                return "cita_form.html"; // ver que devolver
    }
    
}
