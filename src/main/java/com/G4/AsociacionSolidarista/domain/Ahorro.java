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
    private Long id_usuario; // lo interpreta como id_ahorro automaticamente
    private String nombre;
    private long saldo_actual;
    private Long cuota;
    private Long interes_generado;
    private Long tasa_interes;
    
    
    public Ahorro(){
    }

    public Ahorro(Long id_usuario, String nombre, Long saldo_actual, Long cuota, Long interes_generado, Long tasa_interes) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.saldo_actual = saldo_actual;
        this.cuota = cuota;
        this.interes_generado = interes_generado;
        this.tasa_interes = tasa_interes;
    }
    
    

}
