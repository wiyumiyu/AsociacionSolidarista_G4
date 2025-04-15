package com.G4.AsociacionSolidarista.service.impl;

import com.G4.AsociacionSolidarista.dao.RolDao;
import com.G4.AsociacionSolidarista.dao.UsuarioDao;
import com.G4.AsociacionSolidarista.domain.Rol;
import com.G4.AsociacionSolidarista.domain.Usuario;
import com.G4.AsociacionSolidarista.service.CorreoService;
import com.G4.AsociacionSolidarista.service.UsuarioService;
import jakarta.mail.MessagingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private RolDao rolDao;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CorreoService correoService;


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
        if (crearRolUser) {
            try {
                usuario = crearUsuario(usuario);
            } catch (MessagingException ex) {
                Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (!crearRolUser) {
            usuario = modificarUsuario(usuario);
        }

        usuarioDao.save(usuario);
    }

    private Usuario crearUsuario(Usuario usuario) throws MessagingException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Rol rol = new Rol();
        rol.setNombre("ROLE_USER");
        rol.setIdUsuario(usuario.getIdUsuario());
        rolDao.save(rol);

        String password = generarPassword();
        String hash = encoder.encode(password);
        usuario.setPassword(hash);
        
        LocalDate fecha = LocalDate.parse("2020-01-01");
        usuario.setFechaNacimiento(fecha.toString());

        enviarCorreoPassword(usuario, password);

        return usuario;
    }

    @Value("${servidor.http}")
    private String servidor;
    private void enviarCorreoPassword(Usuario usuario, String password) throws MessagingException {
        String mensaje = messageSource.getMessage(
                "usuario.crear.email",
                null, Locale.getDefault());
        mensaje = String.format(
                mensaje, usuario.getNombre(),
                password, servidor + "/login");
        String asunto = messageSource.getMessage(
                "usuario.crear.emailAsunto",
                null, Locale.getDefault());
        correoService.enviarCorreoHtml(usuario.getUsername(), asunto, mensaje);
    }

    private String generarPassword() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuwxyz0123456789+-";
        String password = "";

        for (int i = 0; i < 10; i++) {
            password += caracteres.charAt((int) (Math.random() * caracteres.length()));
        }

        return password;
    }

    private Usuario modificarUsuario(Usuario usuario) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (usuario.getPassword().length() == 0) {
            usuario.setPassword(null);
        }

        Usuario usuarioViejo = usuarioDao.getUsuarioByIdUsuario(usuario.getIdUsuario());
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

        return usuarioViejo;
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.deleteById(usuario.getIdUsuario());
    }

    private void enviaCorreoRecordar(Usuario usuario, String clave) throws MessagingException {
        
        String nombreusuario = usuario.getUsername();
//        System.out.println(nombreusuario);
//        try{
//            nombreusuario = URLEncoder.encode( nombreusuario, "UTF-8") ;
//            
//        }catch(Exception e){}
        
        String mensaje = messageSource.getMessage(""
                + "mp.registro.correo.recordar",
                null,
                Locale.getDefault());
        mensaje = String.format(
                mensaje, usuario.getUsername(),
                servidor,
                nombreusuario, clave);
        String asunto = messageSource.getMessage(
                "mp.registro.mensaje.recordar",
                null, Locale.getDefault());
        correoService.enviarCorreoHtml(
               usuario.getUsername(),
                asunto, mensaje);
    }

    @Override
    public Model recordarUsuario(Model model, Usuario usuario)
            throws MessagingException {
        String mensaje;
        Usuario usuario2 = this.getUsuarioPorUsername(usuario.getUsername());
        if (usuario2 != null) {
            String clave = demeClave();
            usuario2.setPassword(clave);
            this.save(usuario2, false);
            enviaCorreoRecordar(usuario2, clave);
            mensaje = String.format(
                    messageSource.getMessage(
                            "mp.registro.mensaje.recordar.ok",
                            null,
                            Locale.getDefault()),
                    usuario.getUsername());
        } else {
            mensaje = String.format(
                    messageSource.getMessage(
                            "mp.registro.mensaje.usuario.o.correo",
                            null,
                            Locale.getDefault()),
                    usuario.getUsername());
        }
        model.addAttribute(
                "titulo",
                messageSource.getMessage(
                        "mp.registro.activar",
                        null,
                        Locale.getDefault()));
        model.addAttribute(
                "mensaje",
                mensaje);
        return model;
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsername(String username) {
        return usuarioDao.findByUsername(username);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameYPassword(String username, String password) {
        return usuarioDao.findByUsernameAndPassword(username, password);
    }

    private String demeClave() {
        String tira = "ABCDEFGHIJKLMNOPQRSTUXYZabcdefghijklmnopqrstuvwxyz0123456789_*+-";
        String clave = "";
        for (int i = 0; i < 40; i++) {
            clave += tira.charAt((int) (Math.random() * tira.length()));
        }
        return clave;
    }
  
    @Override
    public Model activar(Model model, String username, String clave) {
        
        
        
        Usuario usuario
                = this.getUsuarioPorUsername(username);
        
        
        
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
        } else {
            model.addAttribute(
                    "titulo",
                    messageSource.getMessage(
                            "mp.registro.activar",
                            null, Locale.getDefault()));
            model.addAttribute(
                    "mensaje",
                    messageSource.getMessage(
                            "mp.registro.activar.error",
                            null, Locale.getDefault()));
        }
        return model;
    }
    
         @Override
     public void activar(Usuario usuario) {
         var codigo = new BCryptPasswordEncoder();
         usuario.setPassword(codigo.encode(usuario.getPassword()));
         this.save(usuario, true);
     }

}
