package com.G4.AsociacionSolidarista.controller;

import com.G4.AsociacionSolidarista.domain.Ahorro;
import com.G4.AsociacionSolidarista.domain.Credito;
import com.G4.AsociacionSolidarista.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.G4.AsociacionSolidarista.service.CreditoService;
import com.G4.AsociacionSolidarista.service.UsuarioDetailsService;
import com.G4.AsociacionSolidarista.service.impl.FirebaseStorageServiceImpl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    private UsuarioDetailsService usuarioDetailsService;

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;

    @RequestMapping("/listado")
    public String page(Model model) {
        List<Usuario> listaUsuarios = usuarioDetailsService.getUsuarios(true);
        var creditos = creditoService.getCreditos(false);
        model.addAttribute("creditos", creditos);
        model.addAttribute("totalCreditos", creditos.size());
        model.addAttribute("usuarios", listaUsuarios);

        return "/credito/listado";
    }

    @GetMapping("/listado/{idUsuario}")
    public String getCreditosByUsuario(@PathVariable Long idUsuario, Model model) {
        List<Usuario> listaUsuarios = usuarioDetailsService.getUsuarios(true);
        List<Credito> creditos = creditoService.buscarPorIdUsuario(idUsuario);

        // Crear un nuevo crédito con el idUsuario preestablecido
        Credito credito = new Credito();
        credito.setIdUsuario(idUsuario);
        model.addAttribute("creditos", creditos);
        model.addAttribute("totalCreditos", creditos.size());
        model.addAttribute("usuarios", listaUsuarios);
        model.addAttribute("credito", credito); // Añadir el objeto credito al modelo
        return "/credito/listado";
    }

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

//    @PostMapping("/guardar")
//    public String creditoGuardar(Credito credito) {
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        credito.setCreatedAt(LocalDateTime.now().format(formatter));
//
//        creditoService.save(credito);
//        return "redirect:/credito/listado";
//    }
    @PostMapping("/guardar")
    public String creditoGuardar(Credito credito, Authentication auth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        credito.setCreatedAt(LocalDateTime.now().format(formatter));
        creditoService.save(credito);

        // Verificar si el usuario es admin
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return "redirect:/credito/listado";
        } else {
            return "redirect:/credito/listado/" + credito.getIdUsuario();
        }
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
