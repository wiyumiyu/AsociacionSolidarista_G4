package com.G4.AsociacionSolidarista.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.G4.AsociacionSolidarista.domain.Beneficiario;

public interface BeneficiarioDao extends JpaRepository<Beneficiario, Long>{
    

}
