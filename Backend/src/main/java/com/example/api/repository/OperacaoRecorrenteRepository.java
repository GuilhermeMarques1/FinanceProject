package com.example.api.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.api.model.OpRecorrente;
import com.example.api.model.Operacao;

public interface OperacaoRecorrenteRepository extends CrudRepository<OpRecorrente, Long> {
	@Query("select o from Operacao o where o.id = ?1")
	Optional<OpRecorrente> findById(Long id);

	@Query("select o from Operacao o join OpUnica opUni join OpRecorrente opRc where opUni.data = ?1 OR opRc.dataInicial = ?1")
	Operacao findByDate(LocalDateTime data);

	@Query("select o from Operacao o join Categoria c where c.Nome = ?1")
	Operacao findByCategoria(String categoria);

	@Query("select o from OpRecorrente o where o.usuario.id = :usuarioId")
	List<OpRecorrente> findAllByUsuarioId(@Param("usuarioId") Long usuarioId);

	// @Query("select o from Operacao o join Categoria c groupby c.nome")
	// Operacao GroupByCategoria();
}
