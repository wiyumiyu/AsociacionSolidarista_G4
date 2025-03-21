package com.G4.AsociacionSolidarista.domain;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;



@Data //Generar por debajo los set y get
@Entity
@Table(name = "beneficiario")
public class Beneficiario implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_beneficiario") 
    private Long idBeneficiario; // lo interpreta como id_beneficiario automaticamente
    private Long idUsuario; // lo interpreta como id_Usuario automaticamente
    private String nombre;
    private String cedula;
    private String parentesco;
    private double porcentajeBeneficiario;
    private LocalDateTime  createdAt;
    private LocalDateTime  updatedAt;
    private LocalDateTime  deletedAt;
    
    @ManyToOne
    @JoinColumn(name = "idUsuario", insertable = false, updatable = false)
    private Usuario usuario;

    public Beneficiario(){
    }

    public Beneficiario(Long idUsuario, String nombre, String cedula, String parentesco, double porcentajeBeneficiario, LocalDateTime  createdAt, LocalDateTime  updatedAt, LocalDateTime  deletedAt) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.cedula = cedula;
        this.parentesco = parentesco;
        this.porcentajeBeneficiario = porcentajeBeneficiario;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    
}
