package com.G4.AsociacionSolidarista.service;

import java.util.List;
import com.G4.AsociacionSolidarista.domain.Credito;

public interface CreditoService {

    // Se obtiene un listado de creditos en un List
    public List<Credito> getCreditos(boolean activos);

    // Se obtiene un Credito, a partir del id de un credito
    public Credito getCredito(Credito credito);

    // Se inserta un nuevo credito si el id del credito esta vacío
    // Se actualiza un credito si el id del credito NO esta vacío
    public void save(Credito credito);

    public void cambiarEstado(Long id, int nuevoEstado);

    // Se elimina el credito que tiene el id pasado por parámetro
    //public void delete(Credito credito);    
}
