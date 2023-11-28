package com.vanguardiapropiedades.inmobiliaria.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vanguardiapropiedades.inmobiliaria.entidades.UsuarioEntidad;
import com.vanguardiapropiedades.inmobiliaria.excepciones.MiException;
import com.vanguardiapropiedades.inmobiliaria.servicios.UsuarioServicio;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    // CREATE
    @GetMapping("/registrar")
    public String registrarUsuario() {
        return "Usuario/usuario_form.html";
    }

    @PostMapping("/registro")
    public String registroUsuario(@RequestParam String nombre, @RequestParam String dni, @RequestParam String email,
            @RequestParam String password,
            @RequestParam String password2, ModelMap modelo) throws MiException {
        try {
            usuarioServicio.crearUsuario(nombre, dni, email, password, password2);

            modelo.put("exito", "Usuario registrado con éxito");

            return "Usuario/usuario_form.html";

        } catch (Exception e) {
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            modelo.put("dni", dni);
            modelo.put("error", e.getMessage());
        }
        return "Usuario/usuario_form.html";

    }

    // TODO agregar list

    // UPDATE
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable String id, ModelMap modelo) {
        Optional<UsuarioEntidad> usuario = usuarioServicio.buscarPorId(id);
        if (usuario.isPresent()) {
            modelo.put("usuario", usuario.get());
        } else {
            modelo.put("usuario", null);
        }
        return "Usuario/usuario_mod.html";
    }

    @PostMapping("/editar-usuario/{id}")
    public String editarUsuario(@PathVariable String id, @RequestParam String nombre, @RequestParam String dni,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String password2, @RequestParam(required = false) MultipartFile foto, ModelMap modelo)
            throws MiException {
        try {
            usuarioServicio.editarUsuario(id, nombre, email, password, password2, foto);
            Optional<UsuarioEntidad> usuario = usuarioServicio.buscarPorId(id);
            modelo.put("usuario", usuario.get());
            modelo.put("exito", "Usuario actualizado con éxito");

            return "Usuario/usuario_mod.html";

        } catch (Exception e) {
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            modelo.put("error", e.getMessage());
        }
        return "Usuario/usuario_mod.html";

    }

    // DELETE
    @RequestMapping("/eliminar-usuario/{id}")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String eliminarUsuario(@PathVariable String id, ModelMap modelo) throws MiException {
        usuarioServicio.eliminarUsuario(id);

        return "Usuario/usuario_list.html";
    }

    @GetMapping("/perfil")
    public String perfilUsuario() {
        return "Usuario/usuario_perfil.html";
    }

    @GetMapping("/editar-perfil/{id}")
    public String editarPerfil(@PathVariable String id, ModelMap modelo) {
        UsuarioEntidad usuario = usuarioServicio.buscarPorId(id).get();
        modelo.put("usuario", usuario);
        return "Usuario/usuario_mod.html";
    }

   /** @GetMapping("/listar")
    public String paginarUsuarios(@PageableDefault(page = 0, size = 5) Pageable pageable, Model model) {
        Page<UsuarioEntidad> page = usuarioServicio.listarUsuarios(pageable);
        model.addAttribute("page", page);
        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        return "Usuario/usuario_list.html";
    }*/
}
