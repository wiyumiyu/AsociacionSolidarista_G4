package com.G4.AsociacionSolidarista.domain;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;



@Data //Generar por debajo los set y get
@Entity
@Table(name = "credito")
public class Credito implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credito") 
    private Long idCredito; // lo interpreta como id_credito automaticamente
    //private Long idUsuario; // lo interpreta como id_credito automaticamente
    private String descripcion;
    private Long montoSolicitado;
    private int plazo;
    private Long montoActual;
    private Long cuota;
    private Long tasa;
    private int estado;
    private String fechaAprobacion;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;
    
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

    
    public Credito(){
    }

    public Credito( String descripcion, Long montoSolicitado, int plazo, Long montoActual, Long cuota, Long tasa, int estado, String fechaAprobacion, String createdAt, String updatedAt, String deletedAt) {
        //this.idUsuario = idUsuario;
        this.descripcion = descripcion;
        this.montoSolicitado = montoSolicitado;
        this.plazo = plazo;
        this.montoActual = montoActual;
        this.cuota = cuota;
        this.tasa = tasa;
        this.estado = estado;
        this.fechaAprobacion = fechaAprobacion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }
  
}
