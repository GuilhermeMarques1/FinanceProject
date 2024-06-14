package com.example.api.model;
import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("OpUnica")
public class OpUnica extends Operacao {
    private float valor;
    private LocalDateTime data;

    public float getValor(){
        return valor;
    }

    public LocalDateTime getData(){
        return data;
    }

    public void setValor(float valor){
        this.valor = valor;
    }

    public void setData(LocalDateTime data){
        this.data = data;
    }
}