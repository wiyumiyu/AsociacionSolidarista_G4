package com.G4.AsociacionSolidarista.controller;

import com.G4.AsociacionSolidarista.domain.Beneficiario;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

//    @GetMapping("/")
//    public String home() {
//        return "index"; // Redirige a index.html en templates
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestParam String email, @RequestParam String password) {
//
//        System.out.println(email);
//        System.out.println(password);
//
//        return "redirect:/ahorro/listado";
//    }
    @RequestMapping("/")
    public String index(Model model, HttpSession session) {

        if (session.getAttribute("idUsuario") == null) {
            return "redirect:/login";
        }
        
        Long idUsuario = (Long) session.getAttribute("idUsuario");

        return "index";
    }
    
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }


    @GetMapping("/perfil/{idUsuario}")
    public String getPerfil(@PathVariable Long idUsuario, Model model) {

        /*List<Beneficiario> beneficiarios = beneficiarioService.getBeneficiariosByIdUsuario(idUsuario);

        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setIdUsuario(idUsuario);

        model.addAttribute("beneficiarios", beneficiarios);
        model.addAttribute("beneficiario", beneficiario);
        */
        model.addAttribute("idUsuario", idUsuario);

        return "/profile";
    }

}
