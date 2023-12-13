package com.vanguardiapropiedades.inmobiliaria.controladores;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vanguardiapropiedades.inmobiliaria.entidades.CitaEntidad;
import com.vanguardiapropiedades.inmobiliaria.entidades.PropiedadEntidad;
import com.vanguardiapropiedades.inmobiliaria.excepciones.MiException;
import com.vanguardiapropiedades.inmobiliaria.servicios.CitaServicio;
import com.vanguardiapropiedades.inmobiliaria.servicios.PropiedadServicio;

@Controller
@RequestMapping("/cita")
public class CitaControlador {

    @Autowired
    private CitaServicio citaServicio;

    @Autowired
    private PropiedadServicio propiedadServicio;

    @GetMapping("/crear-cita/{idPropiedad}")
    public String registrarCita(@PathVariable String idPropiedad, ModelMap modelo) {
        PropiedadEntidad propiedad = propiedadServicio.buscarPorId(idPropiedad).get();
        modelo.put("propiedad", propiedad);
        return "Cita/cita_form.html";
    }

    @PostMapping("/crear")
    public String crearCita(String enteid, String clienteid, String propiedadid,
            @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, String nota,
            RedirectAttributes modelo)
            throws MiException {
        try {
            citaServicio.crearCita(enteid, clienteid, propiedadid, fecha, nota);
            modelo.addFlashAttribute("exito", "La cita se creó con éxito");
            // return "Cita/cita_form.html";
            return "redirect:/cita/crear-cita/" + propiedadid;
        } catch (Exception e) {
            modelo.addFlashAttribute("error", "La cita no se pudo solicitar");
            // return "Cita/cita_form.html";
            return "redirect:/cita/crear-cita/" + propiedadid;
        }
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
