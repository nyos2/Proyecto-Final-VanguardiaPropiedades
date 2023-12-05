package com.vanguardiapropiedades.inmobiliaria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vanguardiapropiedades.inmobiliaria.excepciones.MiException;
import com.vanguardiapropiedades.inmobiliaria.servicios.OfertaServicio;

@Controller
@RequestMapping("/oferta")
public class OfertaControlador {

    @Autowired
    private OfertaServicio ofertaServicio;

    @PostMapping("/crear")
    public String crearOferta(Integer valorOferta, String propiedadId, String usuarioId, Model modelo)
            throws MiException {
        try {
            ofertaServicio.crearOferta(valorOferta, propiedadId, usuarioId);
            modelo.addAttribute("exito", "Oferta creada con éxito");
            return "Propiedades/propiedad_list";
        } catch (Exception e) {
            modelo.addAttribute("valorOferta", valorOferta);
            modelo.addAttribute("propiedad", propiedadId);
            modelo.addAttribute("usuario", usuarioId);
            modelo.addAttribute("error", "Oferta no creada");
        }
        // finally {
        return "redirect:/propiedad/listar";
        // }
    }

}
