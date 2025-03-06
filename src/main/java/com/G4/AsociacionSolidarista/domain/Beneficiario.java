package com.G4.AsociacionSolidarista.domain;

import com.google.api.client.util.DateTime;
import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;



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
    private DateTime createdAt;
    private DateTime updatedAt;
    private DateTime deletedAt;

    public Beneficiario(){
    }

    public Beneficiario(Long id_usuario, String nombre, String cedula, String parentesco, double porcentajeBeneficiario, DateTime createdAt, DateTime updatedAt, DateTime deletedAt) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.cedula = cedula;
        this.parentesco = parentesco;
        this.porcentajeBeneficiario = porcentajeBeneficiario;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    
}
