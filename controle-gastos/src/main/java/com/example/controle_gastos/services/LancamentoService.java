package com.example.controle_gastos.services;

import com.example.controle_gastos.dto.DespesaCompletaDTO;
import com.example.controle_gastos.dto.LancamentoDTO;
import com.example.controle_gastos.estructure.entitys.Lancamento;
import com.example.controle_gastos.estructure.repository.LancamentoRepository;
import com.example.controle_gastos.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LancamentoService {

    // Injeção de dependência via construtor gerado pelo Lombok (@RequiredArgsConstructor)
    private final LancamentoRepository lancamentoRepository;

    @Transactional
    public LancamentoDTO salvar(LancamentoDTO dto) {
        Lancamento lancamento = dto.toEntity();
        Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);
        return new LancamentoDTO(lancamentoSalvo);
    }

    public List<LancamentoDTO> listar() {
        return lancamentoRepository.findAll()
                .stream()
                .map(LancamentoDTO::new)
                .toList();
    }

    public void delete(Long id) {
        if(! lancamentoRepository.existsById(Math.toIntExact(id))) {
            throw new ResourceNotFoundException("Id não encontrado");
        }
        lancamentoRepository.deleteById(Math.toIntExact(id));
    }

    public LancamentoDTO atualizar(Long id, LancamentoDTO dto)  {
        Lancamento lancamento = lancamentoRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado para o ID: " + id));

        lancamento.setNome(dto.nome());
        lancamento.setTipo(dto.tipo());
        lancamento.setData(dto.data());
        lancamento.setValor(dto.valor());

        Lancamento novoLancamento = lancamentoRepository.save(lancamento);
        return new LancamentoDTO(novoLancamento);
    }

    public DespesaCompletaDTO sumLancamentoCompleto() {
        BigDecimal entradas = lancamentoRepository.sumLancamentosEntrada();
        BigDecimal saidas = lancamentoRepository.sumLancamentosSaida();
        BigDecimal resumoEntradasMenosSaidas;

        resumoEntradasMenosSaidas = entradas.subtract(saidas);
        return DespesaCompletaDTO.builder()
                .entrada(entradas)
                .saida(saidas)
                .entradaSubtraiSaida(resumoEntradasMenosSaidas)
                .build();
    }

    public void deleteAll() {
        lancamentoRepository.deleteAll();
    }

}