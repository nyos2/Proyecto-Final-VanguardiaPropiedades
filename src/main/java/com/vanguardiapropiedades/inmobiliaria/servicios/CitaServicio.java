package com.vanguardiapropiedades.inmobiliaria.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.vanguardiapropiedades.inmobiliaria.entidades.CitaEntidad;
import com.vanguardiapropiedades.inmobiliaria.entidades.UsuarioEntidad;
import com.vanguardiapropiedades.inmobiliaria.excepciones.MiException;
import com.vanguardiapropiedades.inmobiliaria.repositorios.CitaRepositorio;
import com.vanguardiapropiedades.inmobiliaria.repositorios.UsuarioRepositorio;

public class CitaServicio {
    @Autowired
    CitaRepositorio citaRepositorio;

    UsuarioRepositorio usuarioRepositorio;
    
    
    public void crearCita(String idEnte, String idCliente, String fecha, String hora, String nota) throws MiException {

        
            Optional<UsuarioEntidad> enteOptional = usuarioRepositorio.findById(idEnte);
            Optional<UsuarioEntidad> clienteOptional = usuarioRepositorio.findById(idCliente);
            // Aqui deberia obtene el rango horario, ver como ??, a lo mejor + el id de la propiedad

            if (enteOptional.isPresent() && clienteOptional.isPresent()) {
                // en el if verificar si esta cargado tambien el horario (fecha/hora)
                UsuarioEntidad ente = enteOptional.get();
                UsuarioEntidad cliente = clienteOptional.get();

                CitaEntidad cita = new CitaEntidad();
                cita.setCliente(cliente);
                cita.setEnte(ente);
                // Aqui deberia agregar el set de fecha / horario de la cita 
                cita.setNota(nota);

                citaRepositorio.save(cita);
            } else {
                // >> No se cargaron los datos necesarios adecudamente !!
            }
       

    }
}
