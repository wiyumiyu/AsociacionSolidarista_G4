
package com.G4.AsociacionSolidarista.service;

import java.util.List;
import com.G4.AsociacionSolidarista.domain.Ahorro;
        
public interface AhorroService  {
    
    // Se obtiene un listado de ahorros en un List
    public List<Ahorro> getAhorros(boolean activos);
    
    //get ahorros por cliente
    public List<Ahorro> getAhorrosByIdUsuario(Long idUsuario);
    
    
    public List<Ahorro> buscarPorSaldoActual(Long saldoActual);    
    public List<Ahorro> buscarPorSaldoActualMayorA(Long saldoActual);    
    
    public List<Ahorro> buscarPorUsuarioYSaldo(Long idUsuario, Long saldoActual);    
    
    public List<Ahorro> buscarPorIdUsuarioAndSaldoActualMayorA(Long idUsuario, Long saldoActual);
    
   // Se obtiene un Ahorro, a partir del id de un ahorro
    public Ahorro getAhorro(Ahorro ahorro);
    
    // Se inserta un nuevo ahorro si el id del ahorro esta vacío
    // Se actualiza un ahorro si el id del ahorro NO esta vacío
    public void save(Ahorro ahorro);
    
    // Se elimina el ahorro que tiene el id pasado por parámetro
    public void delete(Ahorro ahorro);    
}
