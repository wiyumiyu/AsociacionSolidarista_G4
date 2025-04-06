package com.G4.AsociacionSolidarista.service.impl;

import com.G4.AsociacionSolidarista.dao.UsuarioDao;
import com.G4.AsociacionSolidarista.domain.Usuario;
import com.G4.AsociacionSolidarista.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    //@Autowired
    //private RolDao rolDao;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios(boolean activos) {
        var lista = usuarioDao.findAll();
        if (activos) {
            lista.removeIf(b -> b.getDeletedAt() != null);
        }
        return lista;
    }
/* 
    @Override
    @Transactional
    public List<Usuario> getUsuariosByIdUsuario(Long idUsuario) {
        return usuarioDao.findByIdUsuario(idUsuario); 
    }
     */
    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Usuario usuario, boolean crearRolUser) {
        /*if (crearRolUser) {  //Si se está creando el usuario, se crea el rol por defecto "USER"
            Rol rol = new Rol();
            rol.setNombre("ROLE_USER");
            rol.setIdUsuario(usuario.getIdUsuario());
            rolDao.save(rol);
        }*/

        if (!crearRolUser) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (usuario.getPassword().length() == 0) {
                usuario.setPassword(null);
            }
            
            Usuario usuarioViejo = usuarioDao.findByUsername(usuario.getUsername());
            usuarioViejo.setUsername(usuario.getUsername());
            usuarioViejo.setNombre(usuario.getNombre());
            usuarioViejo.setDireccion(usuario.getDireccion());
            usuarioViejo.setGenero(usuario.getGenero());
            usuarioViejo.setTelefono(usuario.getTelefono());
            usuarioViejo.setCedula(usuario.getCedula());

            if (usuario.getPassword() != null) {
                String hash = encoder.encode(usuario.getPassword());
                usuarioViejo.setPassword(hash);
            }

            usuario = usuarioViejo;
        }

        usuarioDao.save(usuario);
    }
    /*
    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.deleteById(usuario.getIdUsuario());
    } 
     */
}
