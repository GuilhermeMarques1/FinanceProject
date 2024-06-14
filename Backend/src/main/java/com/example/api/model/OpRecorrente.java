
package com.example.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("OpRecorrente")
public class OpRecorrente extends Operacao {
    private float valorRec;
    private LocalDateTime dataInicial;
    private long intervalo; // Em dias

    public float getValorRec(){
        return valorRec;
    }

    public LocalDateTime getData(){
        return dataInicial;
    }
    public long getIntervalo(){
        return intervalo;
    }

    public void setValor(float valorRec){
        this.valorRec = valorRec;
    }

    public void setData(LocalDateTime dataInicial){
        this.dataInicial = dataInicial;
    }

    public void setIntervalo(long intervalo){
        this.intervalo = intervalo;
    }

}
