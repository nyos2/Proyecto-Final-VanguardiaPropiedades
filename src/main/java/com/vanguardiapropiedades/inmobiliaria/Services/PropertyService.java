package com.vanguardiapropiedades.inmobiliaria.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vanguardiapropiedades.inmobiliaria.Entities.Property;
import com.vanguardiapropiedades.inmobiliaria.Enums.Tipo;
import com.vanguardiapropiedades.inmobiliaria.Exceptions.MyException;
import com.vanguardiapropiedades.inmobiliaria.Repositories.PropertyRepository;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    @Transactional
    public void propertyRegistrer(int precio, String tipo) throws MyException {
        Property property = new Property();
        property.setPrecio(precio);
        property.setTipo(Tipo.valueOf(tipo));
        propertyRepository.save(property);
          
    }

}
