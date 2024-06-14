package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import com.example.api.repository.OpRecorrenteRepository;
import com.example.api.model.OpRecorrente;

@Controller("OpRecorrenteController")
@RequestMapping(value = "/operacaoRecorrente")
public class OpRecorrenteController {

    @Autowired
    private OpRecorrenteRepository opRecorrenteRepository;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @GetMapping(value="/OpRecorrente", produces="application/json")
    public ResponseEntity<List<OpRecorrente>> getAllOperacoes(){
        List<OpRecorrente> operacoes = (List<OpRecorrente>) this.opRecorrenteRepository.findAll();

        return new ResponseEntity(operacoes, HttpStatus.OK);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping(value="/", produces="application/json")
    public ResponseEntity<OpRecorrente> insertNewOperacao(@RequestBody OpRecorrente operacao){

        OpRecorrente produtoSalvo = opRecorrenteRepository.save(operacao);

        return new ResponseEntity(produtoSalvo, HttpStatus.OK);
    } 

}
