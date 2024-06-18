package com.example.api.dto; 

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class OperacaoRecorrenteDTO{
  private String nome;
  private String descricao;
  private Long usuario;
  private Float valor;
  private LocalDateTime dataInicial;
  private LocalDateTime dataFinal;
  private List<Long> categoriasId;
}