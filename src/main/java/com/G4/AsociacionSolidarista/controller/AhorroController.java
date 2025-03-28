package com.G4.AsociacionSolidarista.controller;

import com.G4.AsociacionSolidarista.domain.Ahorro;
import com.G4.AsociacionSolidarista.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.G4.AsociacionSolidarista.service.AhorroService;
import com.G4.AsociacionSolidarista.service.UsuarioDetailsService;
import com.G4.AsociacionSolidarista.service.impl.FirebaseStorageServiceImpl;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/ahorro")
public class AhorroController {

    @Autowired
    private AhorroService ahorroService;

    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;

    @RequestMapping("/listado")
    public String page(Model model) {
        var ahorros = ahorroService.getAhorros(true);
        List<Usuario> usuarios = usuarioDetailsService.getUsuarios(true);

        model.addAttribute("ahorros", ahorros);
        model.addAttribute("totalAhorros", ahorros.size());

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("ahorro", new Ahorro());

        return "/ahorro/listado";
    }

    @GetMapping("/listado/{idUsuario}")
    public String getAhorrosByUsuario(@PathVariable Long idUsuario, Model model) {

        List<Ahorro> ahorros = ahorroService.getAhorrosByIdUsuario(idUsuario);

        Ahorro ahorro = new Ahorro();
        ahorro.setIdUsuario(idUsuario);

        model.addAttribute("ahorro", ahorro);
        model.addAttribute("idUsuario", idUsuario);
        model.addAttribute("ahorros", ahorros);
        model.addAttribute("totalAhorros", ahorros.size());
        return "/ahorro/listado";
    }

    @RequestMapping("/historial")
    public String historial(Model model) {
        var ahorros = ahorroService.getAhorros(false);
        model.addAttribute("ahorros", ahorros);
        model.addAttribute("totalAhorros", ahorros.size());

        return "/ahorro/historial";
    }

    @PostMapping("/guardar")
    public String ahorroGuardar(Ahorro ahorro, Authentication auth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ahorro.setCreatedAt(LocalDateTime.now().format(formatter));
        ahorroService.save(ahorro);

        // Verificar si el usuario es admin
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return "redirect:/ahorro/listado";
        } else {
            return "redirect:/ahorro/listado/" + ahorro.getIdUsuario();
        }
    }

    @GetMapping("/eliminar/{idAhorro}")
    public String ahorroEliminar(Ahorro ahorro) {
        ahorroService.delete(ahorro);
        return "redirect:/ahorro/listado";
    }

    @GetMapping("/modificar/{idAhorro}")
    public String ahorroModificar(Ahorro ahorro, Model model) {
        ahorro = ahorroService.getAhorro(ahorro);
        model.addAttribute("ahorro", ahorro);
        return "/ahorro/modifica";
    }

    @GetMapping("/liquidar/{idAhorro}")
    public String ahorroLiquidar(Ahorro ahorro, Model model) {
        ahorro = ahorroService.getAhorro(ahorro);
        //cambiar saldo a 0
        ahorro.setSaldoActual((long) 0);
        //borrado lógico
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        ahorro.setDeletedAt(timestamp.toString());

        ahorroService.save(ahorro);

        model.addAttribute("ahorro", ahorro);
        return "redirect:/ahorro/historial";
    }
}
