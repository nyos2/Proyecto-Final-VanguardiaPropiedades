package com.vanguardiapropiedades.inmobiliaria.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vanguardiapropiedades.inmobiliaria.entidades.UsuarioEntidad;
import com.vanguardiapropiedades.inmobiliaria.entidades.PropiedadEntidad;
import com.vanguardiapropiedades.inmobiliaria.Enums.Tipo;
import com.vanguardiapropiedades.inmobiliaria.repositorios.UsuarioRepositorio;
import com.vanguardiapropiedades.inmobiliaria.repositorios.PropiedadRepositorio;
import com.vanguardiapropiedades.inmobiliaria.excepciones.MiException;

@Service
public class PropertyService {
    @Autowired
    private PropiedadRepositorio propertyRepository;
    @Autowired
    private UsuarioRepositorio userRepository;

    @Transactional
    public void propertyRegistrer(int precio, String tipo, String idUser) throws MiException {

        Optional<UsuarioEntidad> respuesta = userRepository.findById(idUser);
        PropiedadEntidad property = new PropiedadEntidad();

        if (respuesta.isPresent()) {

            UsuarioEntidad usuario = respuesta.get();

            property.setPrecio(precio);
            property.setTipo(Tipo.valueOf(tipo));
            property.setUsuario(usuario);
            property.setEstado(true);
            propertyRepository.save(property);
        }

    }

    @Transactional
    public void editarProperty(String id, int precio, String tipo, String idUser) throws MiException {
        validar(precio, tipo);

        Optional<UsuarioEntidad> respuesta = userRepository.findById(idUser);
        Optional<PropiedadEntidad> respuestaP = propertyRepository.findById(id);

        if (respuesta.isPresent()) {

            UsuarioEntidad usuario = respuesta.get();

            if (respuestaP.isPresent()) {
                PropiedadEntidad property = respuestaP.get();
                property.setPrecio(precio);
                property.setTipo(Tipo.valueOf(tipo));
                property.setUsuario(usuario);
                property.setEstado(true);
                propertyRepository.save(property);
            }
        }
    }

    public void validar(Integer precio, String tipo) throws MiException {

        if (precio == null) {
            throw new MiException("El precio no puede estar vacio");
        }

        if (tipo.isEmpty() || tipo.isBlank()) {
            throw new MiException("El tipo no puede estar vacio");
        }

    }

    // Listar propiedades
    public List<PropiedadEntidad> listarProperty() {

        List<PropiedadEntidad> propertys = propertyRepository.findAll();
        return propertys;
    }

    // Buscar por tipo de propiedad
    public Optional<PropiedadEntidad> buscarPorTipo(String tipo) {
        PropiedadEntidad property = propertyRepository.findByTipo(tipo);
        return Optional.ofNullable(property);
    }

    // eliminar propiedad
    public void deleteUser(String Id) {
        userRepository.deleteById(Id);
    }

}
