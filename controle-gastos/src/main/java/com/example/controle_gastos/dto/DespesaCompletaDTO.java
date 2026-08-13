package com.example.controle_gastos.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class DespesaCompletaDTO {
    BigDecimal saida;
    BigDecimal entrada;
    BigDecimal entradaSubtraiSaida;
}
