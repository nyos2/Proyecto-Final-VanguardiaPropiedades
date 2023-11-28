package com.vanguardiapropiedades.inmobiliaria.controladores;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// import com.vanguardiapropiedades.inmobiliaria.entidades.UsuarioEntidad;
// import com.vanguardiapropiedades.inmobiliaria.servicios.UsuarioServicio;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
   //  private UsuarioServicio usuarioServicio;
    
    @GetMapping("/dashboard")
    public String panelAdministrativo() {
        return "/Admin/admin_panel.html";
        
    }
    
     @GetMapping("/usuarios")
    public String listar(ModelMap modelo) {
        // List<UsuarioEntidad> usuarios = usuarioServicio.listarUsuarios();
       // modelo.addAttribute("usuarios", usuarios);

        return "usuario_list";
    }
    
        
    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id){
       // usuarioServicio.cambiarRol(id);
        
       return "redirect:/admin/usuarios";
    }
    
}