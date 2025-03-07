
package com.G4.AsociacionSolidarista.controller;

import com.G4.AsociacionSolidarista.domain.Beneficiario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.G4.AsociacionSolidarista.service.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/beneficiario")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService beneficiarioService;
    
    @RequestMapping("/listado")
    public String page(Model model) {
        var beneficiarios = beneficiarioService.getBeneficiarios(true);
        model.addAttribute("beneficiarios", beneficiarios);
        return "/beneficiario/listado";
    }
       
    @PostMapping("/guardar")
    public String beneficiarioGuardar(Beneficiario beneficiario) {        
        beneficiarioService.save(beneficiario);
        return "redirect:/beneficiario/listado";
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