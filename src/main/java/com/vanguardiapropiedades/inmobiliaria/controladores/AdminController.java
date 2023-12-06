package com.vanguardiapropiedades.inmobiliaria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vanguardiapropiedades.inmobiliaria.entidades.PropiedadEntidad;
import com.vanguardiapropiedades.inmobiliaria.servicios.PropiedadServicio;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PropiedadServicio propiedadServicio;

    @GetMapping("/listar-propiedades")
    public String paginarPropiedadesAdmin(@PageableDefault(page = 0, size = 5) Pageable pageable, Model model) {
        Page<PropiedadEntidad> page = propiedadServicio.listarPropiedades(pageable);
        model.addAttribute("page", page);
        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        return "Admin/propiedad_list_admin.html";
    }
}
