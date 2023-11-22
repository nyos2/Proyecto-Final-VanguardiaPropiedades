package com.vanguardiapropiedades.inmobiliaria.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.vanguardiapropiedades.inmobiliaria.Entities.Image;
//import com.vanguardiapropiedades.inmobiliaria.Entities.Image;
import com.vanguardiapropiedades.inmobiliaria.Entities.UserEntity;
import com.vanguardiapropiedades.inmobiliaria.Enums.Rol;
import com.vanguardiapropiedades.inmobiliaria.Exceptions.MyException;
import com.vanguardiapropiedades.inmobiliaria.Repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageService imageService;

    // TODO: Agregar DNI
    @Transactional
    public void userRegister(String nombre, String email, String password, String password2)
            throws MyException {
        UserEntity user = new UserEntity();
        validar(nombre, email, password, password2);
        user.setNombre(nombre);
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setRol(Rol.CLIENT);

        // Image img = imageService.guardarImagen(imagen);
        // user.setImagen(img);

        userRepository.save(user);

    }

    // TODO: Agregar DNI
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

    // TODO Imprement
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity usuario = userRepository.findByEmail(email);
        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority perm = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());

            permisos.add(perm);

            // Luego de validar el usuario guardamos una sesion web
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            // Tiempo de inactividad en segundos para cerrar la sesión
            // session.setMaxInactiveInterval(60);

            // La session contiene los datos del usuario recuperado de la base de datos
            session.setAttribute("usuariosession", usuario);

            return new User(usuario.getEmail(), usuario.getPassword(), permisos);
        } else {
            return null;
        }
    }

    public Optional<UserEntity> buscarPorId(String id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        return Optional.ofNullable(user);
    }

    /**
     * Un CLIENT puede registrarse y modificar sus datos personales, excepto nombre DNI. 
     * Solo podrá ver desde su perfil los inmuebles adquiridos a través de la app o
     * gestionados por un ENTE a través de la app.
     */
    // TODO: Agregar DNI y actualizar los campos correspondientes
    public void editarUsuario(String id, String nombre, String email, String password, String password2,
            MultipartFile foto)
            throws MyException {
        Optional<UserEntity> respuesta = userRepository.findById(id);
        if (respuesta.isPresent()) {
            UserEntity user = respuesta.get();
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
}
