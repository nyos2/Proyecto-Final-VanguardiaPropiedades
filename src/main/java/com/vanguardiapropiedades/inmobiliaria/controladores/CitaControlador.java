package com.vanguardiapropiedades.inmobiliaria.controladores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vanguardiapropiedades.inmobiliaria.entidades.CitaEntidad;
import com.vanguardiapropiedades.inmobiliaria.excepciones.MiException;
import com.vanguardiapropiedades.inmobiliaria.servicios.CitaServicio;

@Controller
@RequestMapping("/cita")
public class CitaControlador {
    
    @Autowired
    private CitaServicio citaServicio;

    @GetMapping("/registrar")
    public String registrarCita() {
        return "cita1_form.html";
    }

    @PostMapping("/crear") 

        public String crearCita(String enteid, String clienteid, String propiedadid, 
        LocalDate fecha, LocalTime hora, String nota, RedirectAttributes redirectAttributes) 
        throws MiException {
                // no me queda claro si tomar propiedad tambien, puede ser necesario
                try {
                    
                    citaServicio.crearCita(enteid, clienteid, propiedadid, fecha, hora, nota);
                    
                    redirectAttributes.addFlashAttribute("exito", "La cita se creo correctamente");
                    return "cita1_form.html";
                } catch (Exception e) {
                    // modelo.addAttribute("error", "la cita no se pudo crear !!");
                    

                    redirectAttributes.addFlashAttribute("error", "la cita no se pudo crear !!");
                    
        }
                return "cita1_form.html"; // ver que devolver o /redirect:/nose"??
    }
    
    @GetMapping("/aceptar/{id}")
    public String aceptarCita(@RequestParam String id, ModelMap modelo) {
        try {
            citaServicio.aceptarCita(id);
            List<CitaEntidad> citas = citaServicio.obtenerTodasLasCitas();
            modelo.put("citas", citas);
            modelo.put("exito", "La cita fue aceptada con éxito");
            return "Propiedades/propiedad_citas.html";
        } catch (Exception e) {

        }
        return citaServicio.aceptarCita(id);
    }

    @GetMapping("/denegar/{id}")
    public String rechazarCita(@RequestParam String id, ModelMap modelo) {
        try {
            citaServicio.rechazarCita(id);
            List<CitaEntidad> citas = citaServicio.obtenerTodasLasCitas();
            modelo.put("citas", citas);
            modelo.put("exito", "La cita se rechazó con éxito");
            return "Propiedades/propiedad_citas.html";
        } catch (Exception e) {
            
        }
        return citaServicio.rechazarCita(id);
    }
}
