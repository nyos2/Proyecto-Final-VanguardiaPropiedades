package com.vanguardiapropiedades.inmobiliaria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String crearOferta(Integer valorOferta, String propiedadId, String usuarioId, ModelMap modelo) throws MiException{
        try {
            ofertaServicio.crearOferta(valorOferta, propiedadId, usuarioId);
            modelo.put("exito", "Oferta creada con éxito");
            return "";
        } catch (Exception e) {
            modelo.put("valorOferta", valorOferta);
            modelo.put("propiedad", propiedadId);
            modelo.put("usuario", usuarioId);
            e.getMessage();
        }
        return "";
    }

}
