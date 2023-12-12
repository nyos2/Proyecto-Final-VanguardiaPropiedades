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
import org.springframework.web.multipart.MultipartFile;

import com.vanguardiapropiedades.inmobiliaria.entidades.PropiedadEntidad;
import com.vanguardiapropiedades.inmobiliaria.entidades.UsuarioEntidad;
import com.vanguardiapropiedades.inmobiliaria.excepciones.MiException;
import com.vanguardiapropiedades.inmobiliaria.servicios.PropiedadServicio;
import com.vanguardiapropiedades.inmobiliaria.servicios.UsuarioServicio;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PropiedadServicio propiedadServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/listar-propiedades")
    public String paginarPropiedadesAdmin(@PageableDefault(page = 0, size = 5) Pageable pageable, Model model) {
        Page<PropiedadEntidad> page = propiedadServicio.listarPropiedades(pageable);
        model.addAttribute("page", page);
        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        return "Admin/propiedad_list_admin.html";
    }

    // TODO controller para lista usuarios admin

    // UPDATE
    @GetMapping("/editar-perfil/{id}")
    public String editarPerfil(@PathVariable String id, ModelMap modelo) {
        UsuarioEntidad usuario = usuarioServicio.buscarPorId(id).get();
        modelo.put("usuario", usuario);
        return "Admin/usuario_mod_admin.html";
    }

    @PostMapping("/editar-usuario/{id}")
    public String editarUsuario(@PathVariable String id, @RequestParam String nombre, @RequestParam String dni,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String password2, @RequestParam(required = false) MultipartFile foto,
            @RequestParam String rol, ModelMap modelo)
            throws MiException {
        try {
            usuarioServicio.editarUsuario(id, dni, nombre, email, password, password2, foto, rol);
            Optional<UsuarioEntidad> usuario = usuarioServicio.buscarPorId(id);
            modelo.put("usuario", usuario.get());
            modelo.put("exito", "Usuario actualizado con éxito");

            return "Admin/usuario_mod_admin.html";

        } catch (Exception e) {
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            modelo.put("error", e.getMessage());
        }
        return "Admin/usuario_mod_admin.html";

    }
}
