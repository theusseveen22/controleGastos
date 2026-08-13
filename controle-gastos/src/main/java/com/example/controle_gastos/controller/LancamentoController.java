package com.example.controle_gastos.controller;

import com.example.controle_gastos.dto.DespesaCompletaDTO;
import com.example.controle_gastos.dto.LancamentoDTO;
import com.example.controle_gastos.estructure.entitys.Lancamento;
import com.example.controle_gastos.services.LancamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LancamentoController {

    private final LancamentoService lancamentoService;

    @RequestMapping("/lancamentos")
    @PostMapping
    public ResponseEntity<LancamentoDTO> criar(@RequestBody @Valid LancamentoDTO dto) {
        LancamentoDTO novoLancamento = lancamentoService.salvar(dto);
        // Retorna o HTTP 201 Created com o objeto salvo na resposta
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLancamento);
    }

    @RequestMapping("/listar")
    @GetMapping
    public ResponseEntity<List<LancamentoDTO>> listar() {
        List<LancamentoDTO> dto = lancamentoService.listar();
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        lancamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<LancamentoDTO> atualizar(@PathVariable long id,
                                                   @Valid @RequestBody LancamentoDTO dto) {
        LancamentoDTO lancamentoAtualizado = lancamentoService.atualizar(id, dto);
        return ResponseEntity.ok(lancamentoAtualizado);
    }

    @RequestMapping("despesas")
    @GetMapping
    public ResponseEntity<DespesaCompletaDTO> despesasTotal() {
        DespesaCompletaDTO total = lancamentoService.sumLancamentoCompleto();
        return ResponseEntity.ok(total);
    }

    @DeleteMapping("delete-all")
    public ResponseEntity<Void> deleteAll() {
        lancamentoService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}