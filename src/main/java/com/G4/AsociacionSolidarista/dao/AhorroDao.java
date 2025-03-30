package com.G4.AsociacionSolidarista.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.G4.AsociacionSolidarista.domain.Ahorro;
import java.util.List;

public interface AhorroDao extends JpaRepository<Ahorro, Long>{
    
    List<Ahorro> findByIdUsuario(Long idUsuario);
    
    List<Ahorro> findBySaldoActual( Long saldoActual);
    List<Ahorro> findBySaldoActualGreaterThan( Long saldoActual);
    
    List<Ahorro> findByIdUsuarioAndSaldoActual(Long idUsuario, Long saldoActual);
    List<Ahorro> findByIdUsuarioAndSaldoActualGreaterThan(Long idUsuario, Long saldoActual);
    
    
    

}
