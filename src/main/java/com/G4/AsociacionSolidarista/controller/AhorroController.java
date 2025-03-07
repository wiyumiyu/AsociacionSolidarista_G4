
package com.G4.AsociacionSolidarista.controller;

import com.G4.AsociacionSolidarista.domain.Ahorro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.G4.AsociacionSolidarista.service.AhorroService;
import com.G4.AsociacionSolidarista.service.impl.FirebaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/ahorro")
public class AhorroController {

    @Autowired
    private AhorroService ahorroService;

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @RequestMapping("/listado")
    public String page(Model model) {
        var ahorros = ahorroService.getAhorros(false);
        model.addAttribute("ahorros", ahorros);
        model.addAttribute("totalAhorros", ahorros.size());
     
        return "/ahorro/listado";
    }
    
    @PostMapping("/guardar")
    public String ahorroGuardar(Ahorro ahorro) {        
        ahorroService.save(ahorro);
        return "redirect:/ahorro/listado";
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

}