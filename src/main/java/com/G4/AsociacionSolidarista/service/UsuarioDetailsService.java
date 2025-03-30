package com.G4.AsociacionSolidarista.service;

import java.util.List;

import org.springframework.security.core.userdetails.*;

import com.G4.AsociacionSolidarista.domain.Usuario;

public interface UsuarioDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    // Se obtiene un listado de beneficiarios en un List
    public List<Usuario> getUsuarios(boolean activos);
    
    public Usuario getUsuarioByIdUsuario(Long idUsuario);
}