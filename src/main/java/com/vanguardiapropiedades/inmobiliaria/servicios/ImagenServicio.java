package com.vanguardiapropiedades.inmobiliaria.servicios;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.vanguardiapropiedades.inmobiliaria.entidades.ImagenEntidad;
import com.vanguardiapropiedades.inmobiliaria.excepciones.MiException;
import com.vanguardiapropiedades.inmobiliaria.repositorios.ImagenRepositorio;

@Service
public class ImagenServicio {

    @Autowired
    private ImagenRepositorio imagenRepositorio;

    // CREATE
    public ImagenEntidad crearImagen(MultipartFile archivo) throws MiException {
        if (archivo != null) {
            try {
                ImagenEntidad imagen = new ImagenEntidad();
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(UUID.randomUUID().toString());
                imagen.setContenido(archivo.getBytes());
                return imagenRepositorio.save(imagen);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    // UPDATE
    public ImagenEntidad editarImagen(MultipartFile archivo, String idImagen) throws MiException {
        if (archivo != null) {
            try {
                ImagenEntidad imagen = new ImagenEntidad();

                if (idImagen != null) {
                    Optional<ImagenEntidad> respuesta = imagenRepositorio.findById(idImagen);
                    if (respuesta.isPresent()) {
                        imagen = respuesta.get();
                    }
                }

                imagen.setMime(archivo.getContentType());
                imagen.setNombre(UUID.randomUUID().toString());
                imagen.setContenido(archivo.getBytes());
                return imagenRepositorio.save(imagen);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    // DELETE
    @Transactional
    public void eliminarImagen(String id) throws MiException {

        imagenRepositorio.deleteById(id);

    }

}
