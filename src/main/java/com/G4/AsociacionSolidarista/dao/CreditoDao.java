package com.G4.AsociacionSolidarista.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.G4.AsociacionSolidarista.domain.Credito;

public interface CreditoDao extends JpaRepository<Credito, Long>{
    

}
