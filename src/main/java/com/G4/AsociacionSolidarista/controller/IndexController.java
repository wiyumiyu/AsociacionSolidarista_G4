
package com.G4.AsociacionSolidarista.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
     @GetMapping("/")
    public String home() {
        return "index"; // Redirige a index.html en templates
    }
    
}
