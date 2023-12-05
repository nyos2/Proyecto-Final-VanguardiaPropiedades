package com.vanguardiapropiedades.inmobiliaria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vanguardiapropiedades.inmobiliaria.excepciones.MiException;
import com.vanguardiapropiedades.inmobiliaria.servicios.OfertaServicio;

@Controller
@RequestMapping("/oferta")
public class OfertaControlador {

    @Autowired
    private OfertaServicio ofertaServicio;

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

}
