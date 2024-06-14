package com.example.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.api.repository.CategoriaRepository;
import com.example.api.model.Categoria;


public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Optional<Categoria> loadCategoriaById(Long id){
        return categoriaRepository.findById(id);
    }
}
