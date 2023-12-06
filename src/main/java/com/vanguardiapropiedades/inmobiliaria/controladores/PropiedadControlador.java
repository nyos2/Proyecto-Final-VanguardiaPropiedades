package com.vanguardiapropiedades.inmobiliaria.controladores;

import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;

import com.vanguardiapropiedades.inmobiliaria.entidades.PropiedadEntidad;
import com.vanguardiapropiedades.inmobiliaria.excepciones.MiException;
import com.vanguardiapropiedades.inmobiliaria.servicios.PropiedadServicio;

@Controller
@RequestMapping("/propiedad")
public class PropiedadControlador {

    @Autowired
    private PropiedadServicio propiedadServicio;

    @GetMapping("/registrar")
    public String registrarPropiedad() {
        return "Propiedades/propiedad_form.html";
    }

    @PostMapping("/registro")
    public String registroPropiedad(@RequestParam Integer precio, @RequestParam String tipo,
            @RequestParam String usuario, @RequestParam String estado, @RequestParam String descripcion,
            @RequestParam String direccion, ModelMap modelo) throws MiException {
        try {
            propiedadServicio.crearPropiedad(precio, tipo, usuario, estado, descripcion, direccion);
            modelo.put("exito", "Propiedad registrada correctamente");
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }
        return "Propiedades/propiedad_form.html";
    }

    @GetMapping("/listar")
    public String paginarPropiedades(@PageableDefault(page = 0, size = 5) Pageable pageable, Model model) {
        Page<PropiedadEntidad> page = propiedadServicio.listarPropiedades(pageable);
        model.addAttribute("page", page);
        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        return "Propiedades/propiedad_list.html";
    }

    @GetMapping("/listar-admin")
    public String paginarPropiedadesAdmin(@PageableDefault(page = 0, size = 5) Pageable pageable, Model model) {
        Page<PropiedadEntidad> page = propiedadServicio.listarPropiedades(pageable);
        model.addAttribute("page", page);
        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        return "Propiedades/propiedad_list_admin.html";
    }

    @GetMapping("/editar/{id}")
    public String editarPropiedad(@PathVariable String id, ModelMap modelo) {
        Optional<PropiedadEntidad> propiedad = propiedadServicio.buscarPorId(id);
        if (propiedad.isPresent()) {
            modelo.put("propiedad", propiedad.get());
        } else {
            modelo.put("propiedad", null);
        }
        return "Propiedades/propiedad_mod.html";
    }

    @PostMapping("/editar/{id}")
    public String editarPropiedad(@PathVariable String id, @RequestParam Integer precio, @RequestParam String tipo,
            @RequestParam(name = "imagen", required = false) List<MultipartFile> imagen , @RequestParam String estado,
            @RequestParam String descripcion, @RequestParam String direccion, ModelMap modelo) {
        try {
            propiedadServicio.editarPropiedad(id, precio, tipo, imagen, estado, descripcion, direccion);
            Optional<PropiedadEntidad> propiedad = propiedadServicio.buscarPorId(id);
            modelo.put("propiedad", propiedad.get());
            modelo.put("exito", "Propiedad actualizada con éxito");

            return "Propiedades/propiedad_mod.html";

        } catch (Exception e) {

            modelo.put("error", e.getMessage());
        }
        return "Propiedades/propiedad_mod.html";
    }

    @RequestMapping("/eliminar-propiedad/{id}")
    public String eliminarPropiedad(@PathVariable String id, @PageableDefault(page = 0, size = 5) Pageable pageable,
            ModelMap model) {
        try {
            propiedadServicio.eliminarPropiedad(id);
            Page<PropiedadEntidad> page = propiedadServicio.listarPropiedades(pageable);
            model.addAttribute("page", page);
            model.addAttribute("currentPage", page.getNumber());
            model.addAttribute("totalItems", page.getTotalElements());
            model.addAttribute("totalPages", page.getTotalPages());
            model.put("exito", "Propiedad eliminada.");
            return "Propiedades/propiedad_list.html";
        } catch (Exception e) {
            Page<PropiedadEntidad> page = propiedadServicio.listarPropiedades(pageable);
            model.addAttribute("page", page);
            model.addAttribute("currentPage", page.getNumber());
            model.addAttribute("totalItems", page.getTotalElements());
            model.addAttribute("totalPages", page.getTotalPages());
            model.put("error", "No se pudo eliminar, compruebe si la propiedad tiene una oferta.");
            return "Propiedades/propiedad_list.html";
        }
    }
}
