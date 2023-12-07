package com.vanguardiapropiedades.inmobiliaria.servicios;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanguardiapropiedades.inmobiliaria.Enums.Oferta;
import com.vanguardiapropiedades.inmobiliaria.entidades.OfertaEntidad;
import com.vanguardiapropiedades.inmobiliaria.entidades.PropiedadEntidad;
import com.vanguardiapropiedades.inmobiliaria.entidades.UsuarioEntidad;
import com.vanguardiapropiedades.inmobiliaria.excepciones.MiException;
import com.vanguardiapropiedades.inmobiliaria.repositorios.OfertaRepositorio;
import com.vanguardiapropiedades.inmobiliaria.repositorios.PropiedadRepositorio;
import com.vanguardiapropiedades.inmobiliaria.repositorios.UsuarioRepositorio;

import jakarta.transaction.Transactional;

@Service
public class OfertaServicio {

    @Autowired
    private OfertaRepositorio ofertaRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PropiedadRepositorio propiedadRepositorio;

    @Transactional
    public void crearOferta(Integer valorOferta, String propiedadId, String usuarioId)
            throws MiException {
        OfertaEntidad oferta = new OfertaEntidad();
        UsuarioEntidad usuario = usuarioRepositorio.findById(usuarioId).get();
        PropiedadEntidad propiedad = propiedadRepositorio.findById(propiedadId).get();
        oferta.setValorOferta(valorOferta);
        oferta.setPropiedad(propiedad);
        oferta.setUsuario(usuario);
        oferta.setEstado(Oferta.PENDIENTE);
        oferta.setFecha(LocalDate.now());
        ofertaRepositorio.save(oferta);
    }

    @Transactional
    public void cambiarEstadoOferta(String id, String estado) throws MiException {
        Optional<OfertaEntidad> respuesta = ofertaRepositorio.findById(id);
        if (respuesta.isPresent()) {
            OfertaEntidad oferta = respuesta.get();
            oferta.setEstado(Oferta.valueOf(estado));
            ofertaRepositorio.save(oferta);
        }
    }

    @Transactional
    public void eliminarOferta(String id) throws MiException {
        ofertaRepositorio.deleteById(id);
    }

}
