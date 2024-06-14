package com.example.api.dto; 

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class OperacaoDTO{
  private String nome;
  private String descricao;
  private Long usuario;
  private List<Long> categoriasId;
}