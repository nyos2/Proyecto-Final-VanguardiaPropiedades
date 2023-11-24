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
    private UsuarioServicio userService;

    @GetMapping("/registrar")
    public String registrar() {
        return "Usuario/usuario_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre,@RequestParam String dni, @RequestParam String email, @RequestParam String password,
            @RequestParam String password2, ModelMap modelo) throws MiException {
        try {
            userService.userRegister(nombre,dni, email, password, password2);

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

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable String id, ModelMap modelo) {
        Optional<UsuarioEntidad> usuario = userService.buscarPorId(id);
        if (usuario.isPresent()) {
            modelo.put("usuario", usuario.get());
        } else {
            modelo.put("usuario", null);
        }
        return "Usuario/usuario_mod.html";
    }

    @PostMapping("/editar-usuario/{id}")
    public String editarUsuario(@PathVariable String id, @RequestParam String nombre, @RequestParam String email,
            @RequestParam String password,
            @RequestParam String password2, @RequestParam(required = false) MultipartFile foto, ModelMap modelo)
            throws MiException {
        try {
            userService.editarUsuario(id,nombre, email, password, password2, foto);

            modelo.put("exito", "Usuario actualizado con éxito");

            return "Usuario/usuario_form.html";

        } catch (Exception e) {
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            modelo.put("error", e.getMessage());
        }
        return "Usuario/usuario_form.html";

    }

}
