package com.G4.AsociacionSolidarista.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.G4.AsociacionSolidarista.domain.Credito;
import com.G4.AsociacionSolidarista.domain.Usuario;
import java.util.List;

public interface CreditoDao extends JpaRepository<Credito, Long>{
   List<Credito> findByUsuario_idUsuario(Long idUsuario);
}



