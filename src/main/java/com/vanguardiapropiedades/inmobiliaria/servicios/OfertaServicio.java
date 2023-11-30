package com.vanguardiapropiedades.inmobiliaria.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanguardiapropiedades.inmobiliaria.Enums.Oferta;
import com.vanguardiapropiedades.inmobiliaria.entidades.OfertaEntidad;
import com.vanguardiapropiedades.inmobiliaria.entidades.PropiedadEntidad;
import com.vanguardiapropiedades.inmobiliaria.entidades.UsuarioEntidad;
import com.vanguardiapropiedades.inmobiliaria.excepciones.MiException;
import com.vanguardiapropiedades.inmobiliaria.repositorios.OfertaRepositorio;

import jakarta.transaction.Transactional;

@Service
public class OfertaServicio {

    @Autowired
    private OfertaRepositorio ofertaRepositorio;

    @Transactional
    public void crearOferta(Integer valorOferta, PropiedadEntidad propiedad, UsuarioEntidad usuario)
            throws MiException {
        OfertaEntidad oferta = new OfertaEntidad();
        oferta.setValorOferta(valorOferta);
        oferta.setPropiedad(propiedad);
        oferta.setUsuario(usuario);
        oferta.setEstado(Oferta.PENDIENTE);
        oferta.setFecha(java.time.LocalDate.now());
        ofertaRepositorio.save(oferta);
    }

    @Transactional
    public void cambiarEstadoOferta(String id, Oferta estado) throws MiException {
        Optional<OfertaEntidad> respuesta = ofertaRepositorio.findById(id);
        if (respuesta.isPresent()) {
            OfertaEntidad oferta = respuesta.get();
            oferta.setEstado(estado);
            ofertaRepositorio.save(oferta);
        }
    }

    @Transactional
    public void eliminarOferta(String id) throws MiException {
        ofertaRepositorio.deleteById(id);
    }

}
