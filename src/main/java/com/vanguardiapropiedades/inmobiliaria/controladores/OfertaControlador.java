package com.vanguardiapropiedades.inmobiliaria.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vanguardiapropiedades.inmobiliaria.entidades.OfertaEntidad;
import com.vanguardiapropiedades.inmobiliaria.excepciones.MiException;
import com.vanguardiapropiedades.inmobiliaria.servicios.OfertaServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/oferta")
public class OfertaControlador {

    @Autowired
    private OfertaServicio ofertaServicio;

    @GetMapping("/ofertas")
    public String listaOfertas(ModelMap modelo) {
        // Obtener la lista de ofertas y agregarla al modelo
        List<OfertaEntidad> ofertas = ofertaServicio.obtenerTodasLasOfertas();
        modelo.put("ofertas", ofertas);
        return "Propiedades/propiedad_ofertas.html";
    }

    // TODO Verificar que para crear oferta no sea el mismo usuario.

    @PostMapping("/crear")
    public String crearOferta(Integer valorOferta, String propiedadId, String usuarioId,
            RedirectAttributes redirectAttributes)
            throws MiException {
        try {
            ofertaServicio.crearOferta(valorOferta, propiedadId, usuarioId);
            redirectAttributes.addFlashAttribute("exito", "La oferta se generó con éxito");
            return "redirect:/propiedad/listar";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "La oferta no se generó");
        }
        return "redirect:/propiedad/listar";

    }

    @GetMapping("/aceptar/{id}")
    public String aceptarOferta(@RequestParam String id, ModelMap modelo) {
        try {
            ofertaServicio.aceptarOferta(id);
            List<OfertaEntidad> ofertas = ofertaServicio.obtenerTodasLasOfertas();
            modelo.put("ofertas", ofertas);
            modelo.put("exito", "La oferta se aceptó con éxito");
            return "Propiedades/propiedad_ofertas.html";
        } catch (Exception e) {

        }
        return ofertaServicio.aceptarOferta(id);
    }

    @GetMapping("/denegar/{id}")
    public String rechazarOferta(@RequestParam String id, ModelMap modelo) {
        try {
            ofertaServicio.rechazarOferta(id);
            List<OfertaEntidad> ofertas = ofertaServicio.obtenerTodasLasOfertas();
            modelo.put("ofertas", ofertas);
            modelo.put("exito", "La oferta se rechazó con éxito");
            return "Propiedades/propiedad_ofertas.html";
        } catch (Exception e) {
            
        }
        return ofertaServicio.rechazarOferta(id);
    }

}
