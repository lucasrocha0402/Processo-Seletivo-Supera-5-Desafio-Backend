package br.com.banco.web;

import br.com.banco.service.MovimentacaoBancariaService;
import br.com.banco.service.response.MovimentacaoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class MovimentacaoBancariaController {

    private final MovimentacaoBancariaService service;

    @GetMapping(value = "/conta-bancaria/{idConta}/movimentacao",
            produces = "application/json")
    public ResponseEntity<MovimentacaoResponse> obterMovimentacaoPorNumeroConta(@PathVariable("idConta") Long idConta,
                                                                                @RequestParam(value = "periodoDataInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime periodoDataInicio,
                                                                                @RequestParam(value = "periodoDataFim", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime periodoDataFim,
                                                                                @RequestParam(value = "operadorNome", required = false) String operadorNome) {
        MovimentacaoResponse response = service.obterMovimentacaoPorNumeroConta(idConta, periodoDataInicio, periodoDataFim, operadorNome);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
