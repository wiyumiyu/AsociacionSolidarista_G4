package com.G4.AsociacionSolidarista.service.impl;

import com.G4.AsociacionSolidarista.dao.AhorroDao;
import com.G4.AsociacionSolidarista.domain.Ahorro;
import com.G4.AsociacionSolidarista.service.AhorroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AhorroServiceImpl implements AhorroService {

    @Autowired
    private AhorroDao ahorroDao;

    @Override
    @Transactional(readOnly = true)
    public List<Ahorro> getAhorros(boolean activos) {
        var lista = ahorroDao.findAll();
        if (activos) {
            lista.removeIf(e -> e.getDeletedAt() != null);
        }
        if (!activos) {
            lista.removeIf(e -> e.getDeletedAt() == null);
        }
        return lista;
    }
    
    @Override
    @Transactional
    public List<Ahorro> getAhorrosByIdUsuario(Long idUsuario) {
        
        var lista = ahorroDao.findByIdUsuario(idUsuario); 
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Ahorro getAhorro(Ahorro ahorro) {
        return ahorroDao.findById(ahorro.getIdAhorro()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Ahorro ahorro) {
        ahorroDao.save(ahorro);
    }

    @Override
    @Transactional
    public void delete(Ahorro ahorro) {
        ahorroDao.deleteById(ahorro.getIdAhorro());
    }

}
