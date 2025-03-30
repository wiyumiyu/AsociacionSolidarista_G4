package com.G4.AsociacionSolidarista.service.impl;

import com.G4.AsociacionSolidarista.domain.Credito;
import com.G4.AsociacionSolidarista.service.CreditoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.G4.AsociacionSolidarista.dao.CreditoDao;
import com.G4.AsociacionSolidarista.domain.Ahorro;
import java.util.Optional;

@Service
public class CreditoServiceImpl implements CreditoService {

    @Autowired
    private CreditoDao creditoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Credito> getCreditos(boolean activos) {
        var lista = creditoDao.findAll();
//        if (activos) {
//            lista.removeIf(e -> !e.isActivo());
//        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Credito getCredito(Credito credito) {
        return creditoDao.findById(credito.getIdCredito()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Credito credito) {
        creditoDao.save(credito);
    }
    
    public void cambiarEstado(Long id, int nuevoEstado) {
        Optional<Credito> optionalCredito = creditoDao.findById(id);
        if (optionalCredito.isPresent()) {
            Credito credito = optionalCredito.get();
            credito.setEstado(nuevoEstado); // Cambia el estado
            creditoDao.save(credito); // Guarda el cambio en la base de datos
        }
    }

//    @Override
//    @Transactional
//    public void delete(Credito credito) {
//        creditoDao.deleteById(credito.getIdCredito());
//    }

    @Override
    public List<Credito> buscarPorIdUsuario(Long idUsuario) {
        return creditoDao.findByUsuario_idUsuario(idUsuario);
    }

}
