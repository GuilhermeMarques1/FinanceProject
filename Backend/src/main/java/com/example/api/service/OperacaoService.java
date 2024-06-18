package com.example.api.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.model.OpUnica;
import com.example.api.model.Operacao;
import com.example.api.repository.OperacaoRepository;

@Service
public class OperacaoService {
    @Autowired
    private OperacaoRepository operacaoRepository;

    public Optional<OpUnica> loadOperacaoById(Long id) {
        return operacaoRepository.findById(id);
    }
    
    public Operacao loadOperacaoByDate(LocalDateTime date){
        return operacaoRepository.findByDate(date);
    }
    
    public Operacao loadOperacaoByCategoria(String categoria){
        return operacaoRepository.findByCategoria(categoria);
    }

    // public Operacao loadOperacoesGroupedByCategoria(){
    //     return operacaoRepository.GroupByCategoria();
    // }


}
