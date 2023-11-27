package com.vanguardiapropiedades.inmobiliaria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vanguardiapropiedades.inmobiliaria.entidades.UsuarioEntidad;
import com.vanguardiapropiedades.inmobiliaria.servicios.UsuarioServicio;

public class ImagenControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping("/perfil/{id}")
    public ResponseEntity<byte[]> imagenUsuario(@PathVariable String id) {
        UsuarioEntidad usuario = usuarioServicio.buscarPorId(id).get();
        byte[] imagen = usuario.getImagen().getContenido();

        HttpHeaders headers = new HttpHeaders(); // estamos devolviendo una imagen

        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }
    
}
