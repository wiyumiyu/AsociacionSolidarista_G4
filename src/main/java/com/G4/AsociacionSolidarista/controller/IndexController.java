package com.G4.AsociacionSolidarista.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        //model.addAttribute("attribute", "value");
        Long idUsuario  = (Long)session.getAttribute("idUsuario");


        return "index";
    } 
    
    
}
