package com.vanguardiapropiedades.inmobiliaria.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.vanguardiapropiedades.inmobiliaria.servicios.UsuarioServicio;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SeguridadWeb {
    @Autowired
    public UsuarioServicio userService;

    // Encryptar contraseñas
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    // Direcciones permitidas y filtros
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> {
                    csrf.disable();
                })
                // Direcciones permitidas
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/admin/**").hasRole("ADMIN");
                    auth.requestMatchers("/**").permitAll();
                    auth.requestMatchers("/user/**").permitAll();
                    // auth.requestMatchers("/").authenticated();
                    // auth.requestMatchers("/css/*", "/js/*", "/img/*", "/registrar", "/registro")
                    // .permitAll();
                    // auth.anyRequest().authenticated();
                })
                // Formulario de login
                .formLogin(form -> {
                    form.loginPage("/login"); // Url de la pagina de login")
                    form.loginProcessingUrl("/logincheck"); // Url del action del formulario
                    form.usernameParameter("email"); // Nombre del input del formulario")
                    form.passwordParameter("password"); // Nombre del input del formulario")
                    form.defaultSuccessUrl("/inicio", true); // Url de inicio correcto
                    form.permitAll();
                })
                .logout(logout -> {
                    logout.logoutUrl("/logout"); // Url de logout
                    logout.logoutSuccessUrl("/"); // Url de redireccionamiento al hacer logout
                    logout.permitAll();
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS); // ALWAYS, IF_REQUIRED, NEVER,
                                                                                 // STATELESS
                    session.maximumSessions(2); // Maximo de sesiones permitidas
                    session.invalidSessionUrl("/"); // Url de redireccionamiento cuando se cierra sesion
                    session.sessionFixation(sessionFixation -> {
                        // migrateSession() - newSession() - none()
                        sessionFixation.migrateSession();
                    });
                    sessionRegistry();

                })
                .build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

}
