
package com.reposteria.sugarfantasy.Service;

import com.reposteria.sugarfantasy.Entity.Usuario;
import com.reposteria.sugarfantasy.repository.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio repositorio;

    public void alta(String username, String nombre, String email, String clave) throws Exception {

        try {
            validar(username, nombre, email, clave);

            Usuario usuario = new Usuario();
            usuario.setUsername(username);
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            String claveEncriptada = new BCryptPasswordEncoder().encode(clave);
            usuario.setClave(claveEncriptada);
            Date now = new Date();
            usuario.setAlta(now);

            repositorio.save(usuario);
        } catch (Exception e) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception(e.getMessage());
        }
    }

    public void modificar(String username, String nombre, String email, String clave) throws Exception {
       
        validar(username, nombre, email, clave);
        Optional<Usuario> respuesta = repositorio.findById(username);

        if (respuesta.isPresent()) {

            try {
                Usuario usuario = respuesta.get();

                usuario.setUsername(username);
                usuario.setNombre(nombre);
                usuario.setEmail(email);
                String claveEncriptada = new BCryptPasswordEncoder().encode(clave);
                usuario.setClave(claveEncriptada);
            } catch (Exception ex) {
                Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
                throw new Exception(ex.getMessage());
            }

        }
    }

    public void validar(String username, String nombre, String email, String clave) throws Exception {
        if (username == null || username.isEmpty()) {
            throw new Exception("Username incorrecto");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("nombre incorrecto");
        }
        if (email == null || email.isEmpty()) {
            throw new Exception("email incorrecto");
        }
        if (clave == null || clave.isEmpty() || clave.length() < 5) {
            throw new Exception("contraseña no puede estar vacia y debe contener mas de 6 caracteres");
        }
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            Optional<Usuario> respuesta = repositorio.findById(username);

            if (respuesta.isPresent()) {
                Usuario usuario = respuesta.get();

                System.out.println(usuario.getUsername());

                List<GrantedAuthority> permisos = new ArrayList();

                GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_ADMIN");
                permisos.add(p1);

                ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                HttpSession session = attr.getRequest().getSession(true);
                session.setAttribute("usuariosession", usuario);
                User user = new User(usuario.getUsername(), usuario.getClave(), permisos);

                return user;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
