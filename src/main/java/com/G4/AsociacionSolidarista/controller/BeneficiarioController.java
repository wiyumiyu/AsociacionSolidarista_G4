package com.G4.AsociacionSolidarista.controller;

import com.G4.AsociacionSolidarista.domain.Beneficiario;
import com.G4.AsociacionSolidarista.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.G4.AsociacionSolidarista.service.BeneficiarioService;
import com.G4.AsociacionSolidarista.service.UsuarioDetailsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/beneficiario")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService beneficiarioService;

    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    @RequestMapping("/listado")
    public String page(Model model) {
        var beneficiarios = beneficiarioService.getBeneficiarios(true);
        List<Usuario> usuarios = usuarioDetailsService.getUsuarios(true);

        model.addAttribute("beneficiarios", beneficiarios);
        model.addAttribute("usuarios", usuarios);

        model.addAttribute("beneficiario", new Beneficiario());

        return "/beneficiario/listado";
    }

    @GetMapping("/listado/{idUsuario}")
    public String getBeneficiariosByUsuario(@PathVariable Long idUsuario, Model model) {

        List<Beneficiario> beneficiarios = beneficiarioService.getBeneficiariosByIdUsuario(idUsuario);

        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setIdUsuario(idUsuario);

        model.addAttribute("beneficiarios", beneficiarios);
        model.addAttribute("beneficiario", beneficiario);
        model.addAttribute("idUsuario", idUsuario);

        return "/beneficiario/listado";
    }

    @PostMapping("/guardar")
    public String beneficiarioGuardar(Beneficiario beneficiario, Authentication auth) {
        beneficiarioService.save(beneficiario);

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return "redirect:/beneficiario/listado";
        } else {
            return "redirect:/beneficiario/listado/" + beneficiario.getIdUsuario();
        }
    }

    @GetMapping("/eliminar/{idBeneficiario}")
    public String beneficiarioEliminar(Beneficiario beneficiario) {
        beneficiarioService.delete(beneficiario);
        return "redirect:/beneficiario/listado";
    }

    @GetMapping("/modificar/{idBeneficiario}")
    public String beneficiarioModificar(Beneficiario beneficiario, Model model) {
        beneficiario = beneficiarioService.getBeneficiario(beneficiario);
        model.addAttribute("beneficiario", beneficiario);
        return "/beneficiario/modifica";
    }
}
