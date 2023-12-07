package com.vanguardiapropiedades.inmobiliaria.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vanguardiapropiedades.inmobiliaria.Enums.Tipo;
import com.vanguardiapropiedades.inmobiliaria.entidades.ImagenEntidad;
import com.vanguardiapropiedades.inmobiliaria.entidades.PropiedadEntidad;
import com.vanguardiapropiedades.inmobiliaria.entidades.UsuarioEntidad;
import com.vanguardiapropiedades.inmobiliaria.excepciones.MiException;
import com.vanguardiapropiedades.inmobiliaria.repositorios.PropiedadRepositorio;
import com.vanguardiapropiedades.inmobiliaria.repositorios.UsuarioRepositorio;

import jakarta.transaction.Transactional;

@Service
public class PropiedadServicio {

    @Autowired
    private PropiedadRepositorio propiedadRepositorio;

    @Autowired
    private ImagenServicio imagenServicio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void crearPropiedad(Integer precio, String tipo, String usuario, String estado, String descripcion,
            String direccion) throws Exception {
        PropiedadEntidad propiedad = new PropiedadEntidad();
        UsuarioEntidad usu = usuarioRepositorio.findById(usuario).orElse(null);
        propiedad.setPrecio(precio);
        propiedad.setTipo(Tipo.valueOf(tipo));
        propiedad.setEstado(Boolean.valueOf(estado));
        propiedad.setDescripcion(descripcion);
        propiedad.setDireccion(direccion);
        propiedad.setUsuario(usu);
        propiedadRepositorio.save(propiedad);
    }

    @Transactional
    public void editarPropiedad(String id, Integer precio, String tipo, List<MultipartFile> imagen, String estado,
            String descripcion, String direccion)
            throws Exception {
        Optional<PropiedadEntidad> respuesta = propiedadRepositorio.findById(id);
        if (respuesta.isPresent()) {
            PropiedadEntidad propiedad = respuesta.get();
            List<ImagenEntidad> propiedades = propiedad.getImagenes();
            if (imagen != null) {
                for (MultipartFile file : imagen) {
                    ImagenEntidad img = imagenServicio.crearImagen(file);
                    propiedades.add(img);
                }
                propiedad.setImagenes(propiedades);
            } else {
                imagen = null;
            }
            propiedad.setPrecio(precio);
            propiedad.setTipo(Tipo.valueOf(tipo));
            propiedad.setEstado(Boolean.valueOf(estado));
            propiedad.setDescripcion(descripcion);
            propiedad.setDireccion(direccion);
            propiedadRepositorio.save(propiedad);
        }
    }

    public void eliminarPropiedad(String id) throws MiException {
        propiedadRepositorio.deleteById(id);
    }

    public Optional<PropiedadEntidad> buscarPorId(String id) {
        PropiedadEntidad propiedad = propiedadRepositorio.findById(id).orElse(null);
        return Optional.ofNullable(propiedad);
    }

    public Page<PropiedadEntidad> listarPropiedades(Pageable pageable) {
        return propiedadRepositorio.findAll(pageable);
    }
}
