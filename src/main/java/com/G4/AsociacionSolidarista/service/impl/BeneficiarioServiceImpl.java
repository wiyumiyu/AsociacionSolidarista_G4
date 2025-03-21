package com.G4.AsociacionSolidarista.service.impl;

import com.G4.AsociacionSolidarista.dao.BeneficiarioDao;
import com.G4.AsociacionSolidarista.domain.Beneficiario;
import com.G4.AsociacionSolidarista.service.BeneficiarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BeneficiarioServiceImpl implements BeneficiarioService {

    @Autowired
    private BeneficiarioDao beneficiarioDao;

    @Override
    @Transactional(readOnly = true)
    public List<Beneficiario> getBeneficiarios(boolean activos) {
        var lista = beneficiarioDao.findAll();
        if (activos) {
            lista.removeIf(b -> b.getDeletedAt() != null);
        }
        return lista;
    }
    
    @Override
    @Transactional
    public List<Beneficiario> getBeneficiariosByIdUsuario(Long idUsuario) {
        return beneficiarioDao.findByIdUsuario(idUsuario); 
    }

    @Override
    @Transactional(readOnly = true)
    public Beneficiario getBeneficiario(Beneficiario beneficiario) {
        return beneficiarioDao.findById(beneficiario.getIdBeneficiario()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Beneficiario beneficiario) {
        beneficiarioDao.save(beneficiario);
    }

    @Override
    @Transactional
    public void delete(Beneficiario beneficiario) {
        beneficiarioDao.deleteById(beneficiario.getIdBeneficiario());
    } 

}
