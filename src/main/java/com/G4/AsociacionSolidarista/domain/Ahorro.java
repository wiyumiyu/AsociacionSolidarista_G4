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
    private long saldoActual;
    private Long cuota;
    private Long interesGenerado;
    private Long tasaInteres;
    
    
    public Ahorro(){
    }

    public Ahorro(Long idUsuario, String nombre, long saldoActual, Long cuota, Long interesGenerado, Long tasaInteres) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.saldoActual = saldoActual;
        this.cuota = cuota;
        this.interesGenerado = interesGenerado;
        this.tasaInteres = tasaInteres;
    }

}
