/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.G4.AsociacionSolidarista.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author fauri
 */
@Controller
@RequestMapping("/backoffice")
public class BackofficeController {
    
    // Controlador requerido para gestionar todas las funcionalidades del backoffice del sistema
    
    // backoffice/users/
    // backoffice/users/save
    // backoffice/users/delete
    
    @RequestMapping("/users")
    public String page(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }
    
}
