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
        return "cita_form.html";
    }

    @PostMapping("/registro") 

        public String registroCita(@RequestParam String idEnte, @RequestParam String idCliente, @RequestParam String idPropiedad, 
        @RequestParam String fechaHora, @RequestParam String nota, ModelMap modelo) throws MiException {
                // no me queda claro si tomar propiedad tambien, puede ser necesario
                try {
                    
                    citaServicio.crearCita(idEnte, idCliente, idPropiedad, fechaHora, nota);
                    // modelo.addAttribute("exito", "La cita se creo correctamente");
                    modelo.put("exito", "La cita se creo correctamente");
                } catch (Exception e) {
                    // modelo.addAttribute("error", "la cita no se pudo crear !!");
                    modelo.put("fechaHora", fechaHora);
                    modelo.put("nota", nota);

                    modelo.put("error", "la cita no se pudo crear !!");
                    return "cita_form.html";
        }
                return "cita_form.html"; // ver que devolver o /redirect:/nose"??
    }
    
}
