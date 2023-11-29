package com.vanguardiapropiedades.inmobiliaria.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.vanguardiapropiedades.inmobiliaria.entidades.ImagenEntidad;
import com.vanguardiapropiedades.inmobiliaria.entidades.UsuarioEntidad;
import com.vanguardiapropiedades.inmobiliaria.Enums.Rol;
import com.vanguardiapropiedades.inmobiliaria.excepciones.MiException;

import jakarta.servlet.http.HttpSession;
import com.vanguardiapropiedades.inmobiliaria.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ImagenServicio imagenServicio;

    // CREATE
    @Transactional
    public void crearUsuario(String nombre, String dni, String email, String password, String password2)
            throws MiException {
        UsuarioEntidad user = new UsuarioEntidad();
        validar(nombre, dni, email, password, password2);
        user.setNombre(nombre);
        user.setEmail(email);
        user.setDni(dni);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setRol(Rol.CLIENT);

        // Image img = imageService.guardarImagen(imagen);
        // user.setImagen(img);

        usuarioRepositorio.save(user);

    }

    /**
     * Un CLIENTE puede registrarse y modificar sus datos personales, excepto nombre
     * y DNI.
     * Solo podrá ver desde su perfil los inmuebles adquiridos a través de la app o
     * gestionados por un ENTE a través de la app.
     */

    // UPDATE
    public void editarUsuario(String id, String dni, String nombre, String email, String password, String password2,
            MultipartFile foto)
            throws MiException {
        Optional<UsuarioEntidad> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            UsuarioEntidad user = respuesta.get();
            validar(nombre, dni, email, password, password2);
            // ? Verificar si el usuario tiene foto
            if (user.getImagen() == null) {
                if (foto.getSize() > 0) {
                    ImagenEntidad img = imagenServicio.crearImagen(foto);
                    user.setImagen(img);
                }
            } else {
                if (foto.getSize() > 0) {
                    ImagenEntidad img = imagenServicio.editarImagen(foto, user.getImagen().getId());
                    user.setImagen(img);
                }
            }
            user.setNombre(nombre);
            user.setEmail(email);
            user.setDni(dni);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            usuarioRepositorio.save(user);
        }
    }

    // DELETE
    @Transactional
    public void eliminarUsuario(String id) throws MiException {

        usuarioRepositorio.deleteById(id);

    }

    private void validar(String nombre, String dni, String email, String password, String password2)
            throws MiException {

        // Verifica si nombre está vacío
        if (nombre.isEmpty() || nombre.isBlank()) {
            throw new MiException("El nombre no puede estar vacio");
        }
        // Verifica si dni está vacío y sea numérico
        if (dni.isEmpty()) {
            throw new MiException("El dni no puede estar vacio");
        }
        // Verifica si dni es numérico con expresion regular que va de 0 a 9
        if (!dni.matches("[0-9]+")) {
            throw new MiException("El dni debe contener solo numéros");
        }
        // Verifica si email está vacío
        if (email.isEmpty()) {
            throw new MiException("El email no puede estar vacio");
        }
        // Verfica email formato correo
        String regex = "([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z0-9]+)\\.([a-z0-9]+))+"; // expresion regular
        Pattern pattern = Pattern.compile(regex); // compilar la expresion regular
        if (!pattern.matcher(email).matches()) {
            throw new MiException("El email no es valido");
        }
        // Verifica si password está vacío
        if (password.isEmpty()) {
            throw new MiException("La contraseña no puede estar vacia");
        }
        // Verfica si las contraseñas coinciden
        if (!password.equals(password2)) {
            throw new MiException("La contraseñas no coinciden");
        }
        // TODO: condiciones de contraseña (cantidad de caracteres, etc)
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioEntidad usuario = usuarioRepositorio.findByEmail(email);
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

    public Optional<UsuarioEntidad> buscarPorId(String id) {
        UsuarioEntidad user = usuarioRepositorio.findById(id).orElse(null);
        return Optional.ofNullable(user);
    }

    public Page<UsuarioEntidad> listarUsuarios(Pageable pageable) {
        return usuarioRepositorio.findAll(pageable);
    }
}