package com.G4.AsociacionSolidarista.domain;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;



@Data //Generar por debajo los set y get
@Entity
@Table(name = "ahorro")
public class Ahorro implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ahorro") 
    private Long idAhorro; // lo interpreta como id_ahorro automaticamente
    private Long idUsuario; // lo interpreta como id_ahorro automaticamente
    private String nombre;
    private Long saldoActual;
    private Long cuota;
    private Long interesGenerado;
    private Long tasaInteres;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;
    
    @ManyToOne
    @JoinColumn(name = "idUsuario", insertable = false, updatable = false)
    private Usuario usuario;
    
    public Ahorro(){
    }

    public Ahorro(Long idUsuario, String nombre, long saldoActual, Long cuota, Long interesGenerado, Long tasaInteres, String createdAt, String updatedAt, String deletedAt) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.saldoActual = saldoActual;
        this.cuota = cuota;
        this.interesGenerado = interesGenerado;
        this.tasaInteres = tasaInteres;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

}
