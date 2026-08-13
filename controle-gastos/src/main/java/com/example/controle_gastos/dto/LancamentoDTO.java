package com.example.controle_gastos.dto;

import com.example.controle_gastos.estructure.entitys.EntradaSaida;
import com.example.controle_gastos.estructure.entitys.Lancamento;
import com.example.controle_gastos.estructure.entitys.TipoDespesaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LancamentoDTO(
        Long id,
        String nome,
        TipoDespesaEnum tipo,
        EntradaSaida entradaOuSaida,
        LocalDate data,
        BigDecimal valor
) {
        public LancamentoDTO(Lancamento lancamento) {
                this(
                        Long.valueOf(lancamento.getId()),
                        lancamento.getNome(),
                        lancamento.getTipo(),
                        lancamento.getEntradaOuSaida(),
                        lancamento.getData(),
                        lancamento.getValor()
                );
        }
        public Lancamento toEntity() {
                Lancamento lancamento = new Lancamento();
                lancamento.setNome(this.nome());
                lancamento.setTipo(this.tipo());
                lancamento.setEntradaOuSaida(this.entradaOuSaida());
                lancamento.setData(this.data());
                lancamento.setValor(this.valor());
                return lancamento;
        }
}
