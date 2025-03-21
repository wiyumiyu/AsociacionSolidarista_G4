
package com.G4.AsociacionSolidarista.service;

import java.util.List;
import com.G4.AsociacionSolidarista.domain.Beneficiario;
        
public interface BeneficiarioService  {
    
    // Se obtiene un listado de beneficiarios en un List
    public List<Beneficiario> getBeneficiarios(boolean activos);
    
   // Se obtiene un Beneficiario, a partir del id de un beneficiario
    public Beneficiario getBeneficiario(Beneficiario beneficiario);
    
    // Se inserta un nuevo beneficiario si el id del beneficiario esta vacío
    // Se actualiza un beneficiario si el id del beneficiario NO esta vacío
    public void save(Beneficiario beneficiario);
    
    // Se elimina el beneficiario que tiene el id pasado por parámetro
    public void delete(Beneficiario beneficiario);    
    
    //get beneficiarios por cliente
    public List<Beneficiario> getBeneficiariosByIdUsuario(Long idUsuario);

}
