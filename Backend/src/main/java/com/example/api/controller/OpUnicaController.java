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

import com.example.api.model.OpUnica;
import com.example.api.repository.OpUnicaRepository;

@Controller("OpUnicaController")
@RequestMapping(value = "/operacaoUnica")
public class OpUnicaController {

    @Autowired
    private OpUnicaRepository opUnicaRepository;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @GetMapping(value = "/OpUnica", produces = "application/json")
    public ResponseEntity<List<OpUnica>> getAllOperacoes() {
        List<OpUnica> operacoes = (List<OpUnica>) this.opUnicaRepository.findAll();

        return new ResponseEntity(operacoes, HttpStatus.OK);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<OpUnica> insertNewOperacao(@RequestBody OpUnica operacaoUnica) {

        OpUnica produtoSalvo = opUnicaRepository.save(operacaoUnica);

        return new ResponseEntity(produtoSalvo, HttpStatus.OK);
    }
}