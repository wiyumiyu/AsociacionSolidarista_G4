package com.G4.AsociacionSolidarista.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name="usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;    
    @NotEmpty
    private String username;    
    @NotEmpty
    private String password;
    
    private String nombre;
    private String direccion;
    private String genero;
    private String telefono;    
    private String cedula;
    private String fechaNacimiento;
    private LocalDateTime  createdAt;
    private LocalDateTime  updatedAt;
    private LocalDateTime  deletedAt;
    
    @OneToMany
    @JoinColumn(name="idUsuario", insertable=false, updatable=false)
    private List<Credito> creditos;
  
    @OneToMany
    @JoinColumn(name = "idUsuario", insertable = false, updatable = false)
    private List<Beneficiario> beneficiarios;
    
    @OneToMany
    @JoinColumn(name="idUsuario")
    private List<Rol> roles;
}