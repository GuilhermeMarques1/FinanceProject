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
import java.util.Optional;

import com.example.api.repository.CategoriaRepository;
import com.example.api.repository.OperacaoRepository;
import com.example.api.repository.UsuarioRepository;

import com.example.api.dto.OperacaoDTO;
import com.example.api.model.Operacao;

import com.example.api.model.Usuario;

@Controller("OperacaoController")
@RequestMapping(value = "/operacao")
public class OperacaoController {
    @Autowired
    private OperacaoRepository operacaoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Operacao>> getAllOperacoes() {
        List<Operacao> operacoes = (List<Operacao>) this.operacaoRepository.findAll();

        return new ResponseEntity(operacoes, HttpStatus.OK);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Operacao> insertNewOperacao(@RequestBody OperacaoDTO operacaoDTO) {

        Optional<Usuario> user = this.usuarioRepository.findById(operacaoDTO.getUsuario());

        if (user.isPresent()) {

            Operacao operacao = Operacao.builder()
                    .nome(operacaoDTO.getNome())
                    .descricao(operacaoDTO.getDescricao())
                    .usuario(user.get())
                    .build();

            Operacao operacaoSalvo = operacaoRepository.save(operacao);
            return new ResponseEntity<>(operacaoSalvo, HttpStatus.OK);
        }
        return new ResponseEntity("User not found", HttpStatus.BAD_REQUEST);
    }

}
