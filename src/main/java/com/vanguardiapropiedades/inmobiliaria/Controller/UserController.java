package com.vanguardiapropiedades.inmobiliaria.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vanguardiapropiedades.inmobiliaria.Exceptions.MyException;
import com.vanguardiapropiedades.inmobiliaria.Services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/registrar")
    public String registrar() {
        return "Usuario/usuario_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password,
            @RequestParam String password2, ModelMap modelo) throws MyException {
        try {
            userService.userRegister(nombre, email, password, password2);

            modelo.put("exito", "Usuario registrado con éxito");

            return "Usuario/usuario_form.html";

        } catch (Exception e) {
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            modelo.put("error", e.getMessage());
        }
        return "Usuario/usuario_form.html";

    }

    @GetMapping("/editar")
    public String editarUsuario() {
        return "Usuario/usuario_mod.html";
    }

    @PostMapping("/editar-usuario")
    public String editarUsuario(@RequestParam String nombre, @RequestParam String email, @RequestParam String password,
            @RequestParam String password2,@RequestParam(required = false) MultipartFile foto, ModelMap modelo) throws MyException {
        try {
            userService.editarUsuario(nombre, email, password, password2, foto);

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
