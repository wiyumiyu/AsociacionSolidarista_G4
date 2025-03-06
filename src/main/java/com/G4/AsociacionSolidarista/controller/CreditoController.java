
package com.G4.AsociacionSolidarista.controller;

import com.G4.AsociacionSolidarista.domain.Credito;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.G4.AsociacionSolidarista.service.CreditoService;
import com.G4.AsociacionSolidarista.service.impl.FirebaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
//    
//    @PostMapping("/guardar")
//    public String categoriaGuardar(Categoria categoria,
//            @RequestParam("imagenFile") MultipartFile imagenFile) {        
//        if (!imagenFile.isEmpty()) {
//            categoriaService.save(categoria);
//            categoria.setRutaImagen(
//                    firebaseStorageService.cargaImagen(
//                            imagenFile, 
//                            "categoria", 
//                            categoria.getIdCategoria()));
//        }
//        categoriaService.save(categoria);
//        return "redirect:/categoria/listado";
//    }
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