package com.vanguardiapropiedades.inmobiliaria.Services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vanguardiapropiedades.inmobiliaria.Entities.Image;
import com.vanguardiapropiedades.inmobiliaria.Exceptions.MyException;
import com.vanguardiapropiedades.inmobiliaria.Repositories.ImageRepository;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public Image guardarImagen(MultipartFile archivo) throws MyException {
        if (archivo != null) {
            try {
                Image image = new Image();
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

    public Image actualizarImagen(MultipartFile archivo, String idImagen) throws MyException {
        if (archivo != null) {
            try {
                Image image = new Image();

                if (idImagen != null) {
                    Optional<Image> respuesta = imageRepository.findById(idImagen);
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
