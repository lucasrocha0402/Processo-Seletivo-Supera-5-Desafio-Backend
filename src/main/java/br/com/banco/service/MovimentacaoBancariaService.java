package br.com.banco.service;

import br.com.banco.domain.Movimentacao;
import br.com.banco.repository.MovimentacaoRepository;
import br.com.banco.repository.specification.MovimentacaoSpecification;
import br.com.banco.service.response.MovimentacaoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimentacaoBancariaService {

    private final MovimentacaoRepository repository;
    private final MovimentacaoSpecification specification;

    public MovimentacaoResponse obterMovimentacaoPorNumeroConta(Long idConta, LocalDateTime periodoDataInicio, LocalDateTime periodoDataFim, String operadorNome) {
        Specification where = this.specification.byFilter(idConta, periodoDataInicio, periodoDataFim, operadorNome);
        List<Movimentacao> list = repository.findAll(where);
        MovimentacaoResponse response = new MovimentacaoResponse();
        response.setSaldoTotal(obterSaldoTotal(idConta));
        response.setSaldoNoPeriodo(obterSaldoPeriodo(list));
        response.setData(list);

        return response;
    }

    private Double obterSaldoTotal(Long idConta) {
        Double saldoTotal = repository.sumSaldoTotal(idConta);

        return saldoTotal;
    }

    private Double obterSaldoPeriodo(List<Movimentacao> list) {
        Double saldoPeriodo = 0.00;
        if(!ObjectUtils.isEmpty(list)) {
            for (Movimentacao movimentacao : list) {
                saldoPeriodo += movimentacao.getValor();
            }
        }

        return saldoPeriodo;
    }

}
