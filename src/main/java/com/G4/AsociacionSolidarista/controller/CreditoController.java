
package com.G4.AsociacionSolidarista.controller;

import com.G4.AsociacionSolidarista.domain.Ahorro;
import com.G4.AsociacionSolidarista.domain.Credito;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.G4.AsociacionSolidarista.service.CreditoService;
import com.G4.AsociacionSolidarista.service.impl.FirebaseStorageServiceImpl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/credito")
public class CreditoController {

    @Autowired
    private CreditoService creditoService;

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @RequestMapping("/listado")
    public String page(Model model) {
        var creditos = creditoService.getCreditos(false);
        model.addAttribute("creditos", creditos);
        model.addAttribute("totalCreditos", creditos.size());
     
        return "/credito/listado";
    }
    
//    @GetMapping("/aprobar/{idCredito}")
//    public String aprobarCredito(Credito credito, Model model) {
//        credito = creditoService.getCredito(credito);
//        model.addAttribute("credito", credito);
//        return "/credito/aprobar";
//    }    
//    @GetMapping("/rechazar/{idCredito}")
//    public String rechazarModificar(Credito credito, Model model) {
//        credito = creditoService.getCredito(credito);
//        model.addAttribute("credito", credito);
//        return "/credito/rechazar";
//    }      
    
    
        @GetMapping("/aprobar/{id}")
    public String aprobarCredito(@PathVariable Long id) {
        creditoService.cambiarEstado(id, 1); // Estado 1 = Aprobado
        return "redirect:/credito/listado";
    }

    @GetMapping("/rechazar/{id}")
    public String rechazarCredito(@PathVariable Long id) {
        creditoService.cambiarEstado(id, 2); // Estado 2 = Rechazado
        return "redirect:/credito/listado";
    }
    
    @PostMapping("/guardar")
    public String creditoGuardar(Credito credito) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        credito.setCreatedAt(LocalDateTime.now().format(formatter));
        creditoService.save(credito);
        return "redirect:/credito/listado";
    }
    
//
//    @GetMapping("/eliminar/{idCategoria}")
//    public String categoriaEliminar(Categoria categoria) {
//        categoriaService.delete(categoria);
//        return "redirect:/categoria/listado";
//    }
//
//    @GetMapping("/modificar/{idCategoria}")
//    public String categoriaModificar(Categoria categoria, Model model) {
//        categoria = categoriaService.getCategoria(categoria);
//        model.addAttribute("categoria", categoria);
//        return "/categoria/modifica";
//    }    

}