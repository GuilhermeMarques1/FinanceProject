package com.example.api.dto; 

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class OperacaoUnicaDTO{
  private String nome;
  private String descricao;
  private Long usuario;
  private String type;
  private Float valor;
  private String data;
  private List<Long> categoriasId;
}