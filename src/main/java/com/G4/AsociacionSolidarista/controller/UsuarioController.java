package com.G4.AsociacionSolidarista.controller;

import com.G4.AsociacionSolidarista.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.G4.AsociacionSolidarista.service.UsuarioService;
import com.G4.AsociacionSolidarista.service.UsuarioDetailsService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    @RequestMapping("/listado")
    public String page(Model model) {
        //List<Usuario> = usuarioService.getUsuarios(true);
        List<Usuario> usuarios = usuarioDetailsService.getUsuarios(true);
        //List<Usuario> usuarios = usuarioDetailsService.getUsuarios(true);

        model.addAttribute("usuarios", usuarios);

        model.addAttribute("usuario", new Usuario());

        return "/usuario/listado";
    }

    @PostMapping("/guardar")
    public String usuarioGuardarPerfil(Usuario usuario, Authentication auth, HttpSession session) {
        
        if (usuario.getIdUsuario() != null) {
            usuarioService.save(usuario, false);
        } else {
            usuarioService.save(usuario, true);
        }
        
        if (session.getAttribute("idUsuario") != usuario.getIdUsuario()) {
            return "redirect:/usuario/listado";
        }

        return "redirect:/logout";
    }

    @GetMapping("/eliminar/{idUsuario}")
    public String usuarioEliminar(Usuario usuario) {
        usuarioService.delete(usuario);
        return "redirect:/usuario/listado";
    }

    @GetMapping("/modificar/{idUsuario}")
    public String usuarioModificar(Usuario usuario, Model model) {
        usuario = usuarioService.getUsuario(usuario);
        model.addAttribute("usuario", usuario);
        return "/usuario/modifica";
    }

}
