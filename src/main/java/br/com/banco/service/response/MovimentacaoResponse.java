package br.com.banco.service.response;

import br.com.banco.domain.Movimentacao;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MovimentacaoResponse {

  @JsonProperty("saldoTotal")
  private Double saldoTotal;

  @JsonProperty("saldoNoPeriodo")
  private Double saldoNoPeriodo;

  @JsonProperty("data")
  private List<Movimentacao> data;

    public MovimentacaoResponse() {

    }
}

