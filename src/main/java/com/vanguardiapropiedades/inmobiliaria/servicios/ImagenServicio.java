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
    private ImagenRepositorio ImagenRepositorio;

    // CREATE
    public ImagenEntidad crearImagen(MultipartFile archivo) throws MiException {
        if (archivo != null) {
            try {
                ImagenEntidad imagen = new ImagenEntidad();
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(UUID.randomUUID().toString());
                imagen.setContenido(archivo.getBytes());
                return ImagenRepositorio.save(imagen);
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
                    Optional<ImagenEntidad> respuesta = ImagenRepositorio.findById(idImagen);
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
