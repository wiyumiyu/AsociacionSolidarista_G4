package com.G4.AsociacionSolidarista.dao;

import com.G4.AsociacionSolidarista.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {    
    
    Usuario findByUsername(String username);
    Usuario getUsuarioByIdUsuario(Long idUsuario);
    Usuario findByUsernameAndPassword(String username, String Password);
    
}