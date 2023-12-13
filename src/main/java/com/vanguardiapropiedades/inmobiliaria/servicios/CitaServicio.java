package com.vanguardiapropiedades.inmobiliaria.servicios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanguardiapropiedades.inmobiliaria.Enums.Cita;

import com.vanguardiapropiedades.inmobiliaria.entidades.CitaEntidad;
import com.vanguardiapropiedades.inmobiliaria.entidades.PropiedadEntidad;
import com.vanguardiapropiedades.inmobiliaria.entidades.UsuarioEntidad;
import com.vanguardiapropiedades.inmobiliaria.excepciones.MiException;
import com.vanguardiapropiedades.inmobiliaria.repositorios.CitaRepositorio;
import com.vanguardiapropiedades.inmobiliaria.repositorios.PropiedadRepositorio;
import com.vanguardiapropiedades.inmobiliaria.repositorios.UsuarioRepositorio;

import jakarta.transaction.Transactional;

@Service
public class CitaServicio {
    @Autowired
    CitaRepositorio citaRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    PropiedadRepositorio propiedadRepositorio;
    
    @Transactional
    public void crearCita(String enteid, String clienteid, String propiedadid, 
        LocalDate fecha, LocalTime hora, String nota) throws MiException {

                UsuarioEntidad cliente = usuarioRepositorio.findById(clienteid).get();
                PropiedadEntidad propiedad = propiedadRepositorio.findById(propiedadid).get();
                
                CitaEntidad cita = new CitaEntidad();
                cita.setCliente(cliente);
                cita.setEnte(propiedad.getUsuario()); // no estoy seguro de lo que hago aqui para saber el dueño ??
                cita.setPropiedad(propiedad); 
                cita.setFecha(fecha); // Adecuat al manejo de fecha
                cita.setHora(hora); // Adecuat al manejo de hora 
                cita.setNota(nota);
                cita.setEstado(Cita.PENDIENTE);

                citaRepositorio.save(cita);
     
    }

@Transactional
    public void cambiarEstadoCita(String id, String estado) throws MiException {
        Optional<CitaEntidad> respuesta = citaRepositorio.findById(id);
        if (respuesta.isPresent()) {
            CitaEntidad cita = respuesta.get();
            cita.setEstado(Cita.valueOf(estado));
            citaRepositorio.save(cita);
        }
    }

    @Transactional
    public void eliminarCita(String id) throws MiException {
        citaRepositorio.deleteById(id);
    }

    @Transactional
    public List<CitaEntidad> obtenerTodasLasCitas() {
        return citaRepositorio.findAll();
    }

    public String aceptarCita(String id) {
        CitaEntidad cita = citaRepositorio.findById(id).get();
        cita.setEstado(Cita.ACEPTADA);
        citaRepositorio.save(cita);
        return null;
    }

    public String rechazarCita(String id) {
        CitaEntidad cita = citaRepositorio.findById(id).get();
        cita.setEstado(Cita.RECHAZADA);
        citaRepositorio.save(cita);
        return null;
    }

}


