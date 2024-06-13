package com.example.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.api.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long>{

}
