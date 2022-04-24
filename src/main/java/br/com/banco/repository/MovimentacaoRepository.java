package br.com.banco.repository;

import br.com.banco.domain.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>, JpaSpecificationExecutor {

    @Query("SELECT SUM(valor) as saldoTotal FROM Movimentacao " +
            "WHERE " +
            "codigoconta = :idConta")
    Double sumSaldoTotal(@Param("idConta") Long idConta);
}
