package com.vanguardiapropiedades.inmobiliaria.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vanguardiapropiedades.inmobiliaria.Enums.Tipo;
import com.vanguardiapropiedades.inmobiliaria.entidades.PropiedadEntidad;
import com.vanguardiapropiedades.inmobiliaria.entidades.UsuarioEntidad;
import com.vanguardiapropiedades.inmobiliaria.excepciones.MiException;
import com.vanguardiapropiedades.inmobiliaria.servicios.PropiedadServicio;

@Controller
@RequestMapping("/propiedad")
public class PropiedadControlador {

    @Autowired
    private PropiedadServicio propiedadServicio;

    @GetMapping("/registrar")
    public String registrarPropiedad(){
        return "Propiedad/propiedad_form.html";
    }

    @PostMapping("/registro")
    public String registroPropiedad(@RequestParam int precio,@RequestParam Tipo tipo,@RequestParam UsuarioEntidad usuario,@RequestParam Boolean estado) throws MiException {
        try {
            propiedadServicio.crearPropiedad(precio, tipo, usuario, estado);
        } catch (Exception e) {
            
        }
        return "Propiedad/propiedad_form.html";
    }

    @GetMapping("/listar")
    public String paginarPropiedades(@PageableDefault(page = 0, size = 5) Pageable pageable, Model model){
        Page<PropiedadEntidad> page = propiedadServicio.listarPropiedades(pageable);
        model.addAttribute("page", page);
        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages()); 
        return "Propiedad/propiedad_list.html";
    }

    @GetMapping("/editar/{id}")
    public String editarPropiedad(@PathVariable String id, ModelMap modelo) {
        Optional<PropiedadEntidad> propiedad = propiedadServicio.buscarPorId(id);
        if (propiedad.isPresent()) {
            modelo.put("propiedad", propiedad.get());
        } else {
            modelo.put("propiedad", null);
        }
        return "Propiedad/propiedad_mod.html";
    }
}
