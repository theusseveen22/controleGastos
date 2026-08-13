package com.example.controle_gastos.estructure.repository;

import com.example.controle_gastos.estructure.entitys.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {
    @Query("SELECT COALESCE(SUM(l.valor), 0) FROM Lancamento l WHERE l.entradaOuSaida = 'SAIDA'")
    BigDecimal sumLancamentosSaida();

    @Query("SELECT COALESCE(SUM(l.valor), 0) FROM Lancamento l WHERE l.entradaOuSaida = 'ENTRADA'")
    BigDecimal sumLancamentosEntrada();

}
