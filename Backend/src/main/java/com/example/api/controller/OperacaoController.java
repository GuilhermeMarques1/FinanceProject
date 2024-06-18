package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.example.api.repository.CategoriaRepository;
import com.example.api.repository.OperacaoRecorrenteRepository;
import com.example.api.repository.OperacaoUnicaRepository;
import com.example.api.repository.UsuarioRepository;

import io.jsonwebtoken.security.Jwks.OP;

import com.example.api.dto.OperacaoRecorrenteDTO;
import com.example.api.dto.OperacaoUnicaDTO;
import com.example.api.model.OpRecorrente;
import com.example.api.model.OpUnica;
import com.example.api.model.Operacao;

import com.example.api.model.Usuario;

@Controller("OperacaoController")
@RequestMapping(value = "/operacao")
public class OperacaoController {
	@Autowired
	private OperacaoUnicaRepository operacaoUnicaRepository;

	@Autowired
	private OperacaoRecorrenteRepository operacaoRecorrenteRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<? extends Operacao>> getAllOperacoes() {
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long id = usuario.getId();		

		List<OpUnica> operacoesUnicas = (List<OpUnica>) this.operacaoUnicaRepository.findAllByUsuarioId(id);
		List<OpRecorrente> operacoesRecorrentes = (List<OpRecorrente>) this.operacaoRecorrenteRepository.findAllByUsuarioId(id);

		Map<String, List<?>> operacoesMap = new HashMap<>();
		operacoesMap.put("operacoesUnicas", operacoesUnicas);
		operacoesMap.put("operacoesRecorrentes", operacoesRecorrentes);

		return new ResponseEntity(operacoesMap, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/unica", produces = "application/json")
	public ResponseEntity<OpUnica> insertNewOperacaoUnica(@RequestBody OperacaoUnicaDTO operacaoDTO) {

		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Optional<Usuario> user = this.usuarioRepository.findById(usuario.getId());

		if (user.isPresent()) {
			OpUnica operacao = OpUnica.builder()
				.nome(operacaoDTO.getNome())
				.descricao(operacaoDTO.getDescricao())
				.usuario(user.get())
				.valor(operacaoDTO.getValor())
				.type(operacaoDTO.getType())
				.data(operacaoDTO.getData())
				.build();

			
			OpUnica operacaoSalvo = operacaoUnicaRepository.save(operacao);
			return new ResponseEntity<>(operacaoSalvo, HttpStatus.OK);
		}
		return new ResponseEntity("User not found", HttpStatus.BAD_REQUEST);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/recorrente", produces = "application/json")
	public ResponseEntity<OpRecorrente> insertNewOperacaoRecorrente(@RequestBody OperacaoRecorrenteDTO operacaoDTO) {

		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Optional<Usuario> user = this.usuarioRepository.findById(usuario.getId());

		if (user.isPresent()) {
			OpRecorrente operacao = OpRecorrente.builder()
				.nome(operacaoDTO.getNome())
				.descricao(operacaoDTO.getDescricao())
				.usuario(user.get())
				.valor(operacaoDTO.getValor())
				.type(operacaoDTO.getType())
				.dataInicial(operacaoDTO.getDataInicial())
				.dataFinal(operacaoDTO.getDataFinal())
				.build();

			OpRecorrente operacaoSalvo = operacaoRecorrenteRepository.save(operacao);
			return new ResponseEntity<>(operacaoSalvo, HttpStatus.OK);
		}
		return new ResponseEntity("User not found", HttpStatus.BAD_REQUEST);
	}

}
