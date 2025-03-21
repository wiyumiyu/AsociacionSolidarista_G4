package com.G4.AsociacionSolidarista.dao;

import com.G4.AsociacionSolidarista.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {    
    Usuario findByUsername(String username);
    
}