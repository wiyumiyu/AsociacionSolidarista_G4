package com.G4.AsociacionSolidarista.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.G4.AsociacionSolidarista.domain.Ahorro;

public interface AhorroDao extends JpaRepository<Ahorro, Long>{
    

}
