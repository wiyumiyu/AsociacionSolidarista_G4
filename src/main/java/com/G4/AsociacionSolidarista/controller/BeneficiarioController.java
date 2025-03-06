
package com.G4.AsociacionSolidarista.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.G4.AsociacionSolidarista.service.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;

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


}