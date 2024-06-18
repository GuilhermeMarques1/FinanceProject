package com.example.api.repository;


import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.api.model.OpUnica;
import com.example.api.model.Operacao;

public interface OperacaoRepository extends CrudRepository<OpUnica, Long> {
    @Query("select o from Operacao o where o.id = ?1")
	Optional<OpUnica> findById(Long id);

    @Query("select o from Operacao o join OpUnica opUni join OpRecorrente opRc where opUni.data = ?1 OR opRc.dataInicial = ?1")
    Operacao findByDate(LocalDateTime data);

    @Query("select o from Operacao o join Categoria c where c.Nome = ?1")
    Operacao findByCategoria(String categoria);

    // @Query("select o from Operacao o join Categoria c groupby c.nome")
    // Operacao GroupByCategoria();
}
