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
    private Long id_usuario; // lo interpreta como id_credito automaticamente
    private String nombre;
    private Long monto_solicitado;
    private int plazo;
    private Long monto_actual;
    private Long cuota;
    private Long tasa;
    private int estado;
    private String fecha_aprobacion;
    
    
    public Credito(){
    }

    public Credito(Long id_usuario, String nombre, Long monto_solicitado, int plazo, Long monto_actual, Long cuota, Long tasa, int estado, String fecha_aprobacion) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.monto_solicitado = monto_solicitado;
        this.plazo = plazo;
        this.monto_actual = monto_actual;
        this.cuota = cuota;
        this.tasa = tasa;
        this.estado = estado;
        this.fecha_aprobacion = fecha_aprobacion;
    }


    
    

}
