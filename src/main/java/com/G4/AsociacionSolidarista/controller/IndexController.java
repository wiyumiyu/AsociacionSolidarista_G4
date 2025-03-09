package com.G4.AsociacionSolidarista.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @GetMapping("/")
    public String home() {
        return "index"; // Redirige a index.html en templates
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {

        System.out.println(email);
        System.out.println(password);

        return "redirect:/ahorro/listado";
    }
}
