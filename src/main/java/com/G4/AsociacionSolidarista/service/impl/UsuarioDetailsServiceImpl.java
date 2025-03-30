
package com.G4.AsociacionSolidarista.service.impl;

import com.G4.AsociacionSolidarista.service.UsuarioDetailsService;
import com.G4.AsociacionSolidarista.dao.UsuarioDao;
import com.G4.AsociacionSolidarista.domain.Usuario;
import com.G4.AsociacionSolidarista.domain.Rol;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UsuarioDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService {
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private HttpSession session;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Busca el usuario por el username en la tabla
        Usuario usuario = usuarioDao.findByUsername(username);
        //Si no existe el usuario lanza una excepción
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        session.removeAttribute("idUsuario");
        session.setAttribute("idUsuario", usuario.getIdUsuario());
        session.setAttribute("nombre", usuario.getNombre());
        session.setAttribute("username", usuario.getUsername());
        session.setAttribute("cedula", usuario.getCedula());
        session.setAttribute("genero", usuario.getGenero());
        session.setAttribute("telefono", usuario.getTelefono());
        session.setAttribute("direccion", usuario.getDireccion());
        
        //Si está acá es porque existe el usuario... sacamos los roles que tiene
        var roles = new ArrayList<GrantedAuthority>();
        for (Rol rol : usuario.getRoles()) {   //Se sacan los roles
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        //Se devuelve User (clase de userDetails)
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios(boolean activos) {
        var lista = usuarioDao.findAll();
        if (activos) {
            lista.removeIf(b -> b.getDeletedAt() != null);
        }
        return lista;
    }

    @Override
    public Usuario getUsuarioByIdUsuario(Long idUsuario) {
       return  usuarioDao.getUsuarioByIdUsuario(idUsuario);
        
    }
}