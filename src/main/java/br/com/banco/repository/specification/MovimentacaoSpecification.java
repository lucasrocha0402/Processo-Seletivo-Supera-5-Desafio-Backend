package br.com.banco.repository.specification;

import br.com.banco.domain.Movimentacao;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class MovimentacaoSpecification {

    public Specification<Movimentacao> movimentacaoContaIdIgual(Long idConta) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("codigoconta"), idConta);
    }

    public Specification<Movimentacao> operadorNomeIgualA(String operadorNome) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(root.get("operador")), operadorNome.toLowerCase());
    }

    public Specification<Movimentacao> movimentacaoAnteriorOuIgualA(LocalDateTime dataFinal) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("data"), dataFinal);
    }

    public Specification<Movimentacao> movimentacaoPosteriorOuIgualA(LocalDateTime dataInicial) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("data"), dataInicial);
    }

    public Specification<Movimentacao> byFilter(Long idConta, LocalDateTime periodoDataInicio, LocalDateTime periodoDataFim, String operadorNome) {
        Specification where = null;

        if (!ObjectUtils.isEmpty(idConta) && idConta > 0) {
            where = and(where, movimentacaoContaIdIgual(idConta));
        }

        if (!ObjectUtils.isEmpty(operadorNome)) {
            where = and(where, operadorNomeIgualA(operadorNome));
        }

        if (!ObjectUtils.isEmpty(periodoDataInicio)) {
            where = and(where, movimentacaoPosteriorOuIgualA(periodoDataInicio));
        }

        if (!ObjectUtils.isEmpty(periodoDataFim)) {
            where = and(where, movimentacaoAnteriorOuIgualA(periodoDataFim));
        }

        return where;
    }

    public Specification and(Specification newWhere, Specification newAdd){
        if(newWhere == null) {
            return where(newAdd);
        } else {
            return where(newWhere).and(newAdd);
        }
    }
}
