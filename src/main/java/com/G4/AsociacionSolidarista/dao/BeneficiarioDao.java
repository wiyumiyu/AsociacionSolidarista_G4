package com.G4.AsociacionSolidarista.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.G4.AsociacionSolidarista.domain.Beneficiario;
import java.util.List;

public interface BeneficiarioDao extends JpaRepository<Beneficiario, Long>{

    List<Beneficiario> findByIdUsuario(Long idUsuario);
}
