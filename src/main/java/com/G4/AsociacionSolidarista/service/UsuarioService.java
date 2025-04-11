
package com.G4.AsociacionSolidarista.service;

import java.util.List;
import com.G4.AsociacionSolidarista.domain.Usuario;
import org.springframework.stereotype.Service;

public interface UsuarioService {
    
    // Se obtiene un listado de usuarios en un List
   public List<Usuario> getUsuarios(boolean activos);
    
   // Se obtiene un Usuario, a partir del id de un usuario
   public Usuario getUsuario(Usuario usuario);
    
    // Se inserta un nuevo usuario si el id del usuario esta vacío
    // Se actualiza un usuario si el id del usuario NO esta vacío
    public void save(Usuario usuario, boolean crearRolUser);
    
    // Se elimina el usuario que tiene el id pasado por parámetro
    public void delete(Usuario usuario);    
    
    //get usuarios por cliente
    //public List<Usuario> getUsuariosByIdUsuario(Long idUsuario);

}
