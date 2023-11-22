package com.vanguardiapropiedades.inmobiliaria.Services;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.vanguardiapropiedades.inmobiliaria.Entities.Image;
//import com.vanguardiapropiedades.inmobiliaria.Entities.Image;
import com.vanguardiapropiedades.inmobiliaria.Entities.User;
import com.vanguardiapropiedades.inmobiliaria.Enums.Rol;
import com.vanguardiapropiedades.inmobiliaria.Exceptions.MyException;
import com.vanguardiapropiedades.inmobiliaria.Repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageService imageService;

    @Transactional
    public void userRegister(String nombre, String email, String password, String password2)
            throws MyException {
        User user = new User();
        validar(nombre, email, password, password2);
        user.setNombre(nombre);
        user.setEmail(email);
        user.setPassword(password);
        user.setRol(Rol.CLIENT);

        // Image img = imageService.guardarImagen(imagen);
        // user.setImagen(img);

        userRepository.save(user);

    }

    private void validar(String nombre, String email, String password, String password2) throws MyException {

        // verificar que el email sea valido
        String regex = "([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z0-9]+)\\.([a-z0-9]+))+"; // expresion regular
        Pattern pattern = Pattern.compile(regex); // compilar la expresion regular

        if (nombre.isEmpty() || nombre.isBlank()) {
            throw new MyException("El nombre no puede estar vacio");
        }
        if (email.isEmpty()) {
            throw new MyException("El email no puede estar vacio");
        }

        if (!pattern.matcher(email).matches()) {
            throw new MyException("El email no es valido");
        }

        if (password.isEmpty()) {
            throw new MyException("La contraseña no puede estar vacia");
        }
        if (!password.equals(password2)) {
            throw new MyException("La contraseñas no coinciden");
        }

    }

    public void editarUsuario(String nombre, String email, String password, String password2, MultipartFile foto)
            throws MyException {
        User user = userRepository.findByEmail(email);
        validar(nombre, email, password, password2);
        if (foto != null) {
            Image img = imageService.guardarImagen(foto);
            user.setImagen(img);
        }
        user.setNombre(nombre);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
    }
}
