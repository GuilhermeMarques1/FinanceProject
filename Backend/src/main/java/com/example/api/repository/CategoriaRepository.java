package com.example.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.api.model.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
    @Query("select c from Categoria c where c.id = ?1")
	Optional<Categoria> findById(Long id);
}
