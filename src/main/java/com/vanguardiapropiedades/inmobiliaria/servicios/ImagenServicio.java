package com.vanguardiapropiedades.inmobiliaria.servicios;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vanguardiapropiedades.inmobiliaria.entidades.ImagenEntidad;
import com.vanguardiapropiedades.inmobiliaria.excepciones.MiException;
import com.vanguardiapropiedades.inmobiliaria.repositorios.ImagenRepositorio;

@Service
public class ImagenServicio {
    @Autowired
    private ImagenRepositorio imageRepository;

    public ImagenEntidad guardarImagen(MultipartFile archivo) throws MiException {
        if (archivo != null) {
            try {
                ImagenEntidad image = new ImagenEntidad();
                image.setMime(archivo.getContentType());
                image.setNombre(UUID.randomUUID().toString());
                image.setContenido(archivo.getBytes());
                return imageRepository.save(image);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    

    public ImagenEntidad actualizarImagen(MultipartFile archivo, String idImagen) throws MiException {
        if (archivo != null) {
            try {
                ImagenEntidad image = new ImagenEntidad();

                if (idImagen != null) {
                    Optional<ImagenEntidad> respuesta = imageRepository.findById(idImagen);
                    if (respuesta.isPresent()) {
                        image = respuesta.get();
                    }
                }

                image.setMime(archivo.getContentType());
                image.setNombre(UUID.randomUUID().toString());
                image.setContenido(archivo.getBytes());
                return imageRepository.save(image);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;

    }
}
