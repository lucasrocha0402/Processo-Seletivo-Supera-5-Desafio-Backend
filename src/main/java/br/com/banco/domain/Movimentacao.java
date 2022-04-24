package br.com.banco.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="transferencia")
public class Movimentacao {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    @Column(name = "data_transferencia")
    private LocalDateTime data;

    @NotNull
    @Column(name = "tipo")
    private String tipo;

    @NotNull
    @Column(name = "valor", columnDefinition="Decimal(20,2)")
    private Double valor;

    @Column(name = "nome_operador_transacao")
    private String operador;

    @NotNull
    @Column(name = "conta_id")
    private Long codigoconta;
}
