package com.vanguardiapropiedades.inmobiliaria.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vanguardiapropiedades.inmobiliaria.Entities.Property;
import com.vanguardiapropiedades.inmobiliaria.Entities.UserEntity;
import com.vanguardiapropiedades.inmobiliaria.Enums.Tipo;
import com.vanguardiapropiedades.inmobiliaria.Exceptions.MyException;
import com.vanguardiapropiedades.inmobiliaria.Repositories.PropertyRepository;
import com.vanguardiapropiedades.inmobiliaria.Repositories.UserRepository;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void propertyRegistrer(int precio, String tipo, String idUser) throws MyException {

        Optional<UserEntity> respuesta = userRepository.findById(idUser);
        Property property = new Property();

        if (respuesta.isPresent()) {

            UserEntity usuario = respuesta.get();

            property.setPrecio(precio);
            property.setTipo(Tipo.valueOf(tipo));
            property.setUsuario(usuario);
            property.setEstado(true);
            propertyRepository.save(property);
        }
    }

    public void validar(Integer precio, String tipo) throws MyException{

        if (precio == null) {
            throw new MyException("El precio no puede estar vacio");
        }

        if (tipo.isEmpty() || tipo.isBlank()) {
            throw new MyException("El tipo no puede estar vacio");
        }

    }
    // Listar propiedades
    public List<Property> listarProperty(){
        
        List<Property> propertys = propertyRepository.findAll();
        return propertys;
    }
    //Buscar por tipo de propiedad
    public Optional<Property> buscarPorTipo(String tipo) {
        Property property = propertyRepository.findByTipo(tipo);
        return Optional.ofNullable(property);
    }


}
