package com.vanguardiapropiedades.inmobiliaria.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    UsuarioRepositorio usuarioRepositorio;

    // @Autowired
    PropiedadRepositorio propiedadRepositorio;
    
    @Transactional
    public void crearCita(String idEnte, String idCliente, String idPropiedad, String fechaHora, String nota) throws MiException {

        
            Optional<UsuarioEntidad> enteRepuesta = usuarioRepositorio.findById(idEnte);
            Optional<UsuarioEntidad> clienteRepuesta = usuarioRepositorio.findById(idCliente);
            Optional<PropiedadEntidad> propiedadRepuesta = propiedadRepositorio.findById(idPropiedad);
            // Aqui deberia analizar si realmente es necesario el rollo anterior o solo guardar id / validar el horario  dentro de un rango, ver como ?? 

            if (enteRepuesta.isPresent() && clienteRepuesta.isPresent() && propiedadRepuesta.isPresent()) {
                // en el if verificar si esta cargado tambien el horario (fecha/hora)
                UsuarioEntidad ente = enteRepuesta.get();
                UsuarioEntidad cliente = clienteRepuesta.get();
                PropiedadEntidad propiedad = propiedadRepuesta.get();

                CitaEntidad cita = new CitaEntidad();
                cita.setCliente(cliente);
                cita.setEnte(ente);
                cita.setPropiedad(propiedad); // No estoy convencido, pero para la vista me sirve. Analizar la entidad Cita
                cita.setFechahora(fechaHora); // Aqui deberia agregar el set de fecha / horario de la cita 
                cita.setNota(nota);
                cita.setAcepto(false);

                citaRepositorio.save(cita);
            } else {
                // >> No estan los datos necesarios !!
                // System.out.println(" no deberia ocurrir, que paso ???");
            }
       

    }
}
