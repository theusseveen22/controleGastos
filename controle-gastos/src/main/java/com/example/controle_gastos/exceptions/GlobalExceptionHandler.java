package com.example.controle_gastos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j; // Se tiver Lombok

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Trata exceções de recurso não encontrado (HTTP 404)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException ex) {

        // ProblemDetail é a classe nativa do Spring Boot 3+ (RFC 7807)
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );

        problemDetail.setTitle("Recurso Não Encontrado");
        problemDetail.setType(URI.create("https://api.suaempresa.com/errors/not-found"));
        problemDetail.setProperty("timestamp", Instant.now()); // Adiciona campos extras dinamicamente

        return problemDetail;
    }

    // Trata erros de validação de formulário/DTO (@Valid) (HTTP 400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationErrors(MethodArgumentNotValidException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Um ou mais campos estão inválidos"
        );

        problemDetail.setTitle("Erro de Validação");
        problemDetail.setProperty("timestamp", Instant.now());

        // Mapeia os campos que falharam na validação
        Map<String, String> invalidFields = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                invalidFields.put(error.getField(), error.getDefaultMessage())
        );

        // Adiciona a lista de erros diretamente na propriedade do ProblemDetail
        problemDetail.setProperty("invalidFields", invalidFields);

        return problemDetail;
    }

    // Intercepta qualquer outro erro não tratado (HTTP 500)
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUncaughtException(Exception ex) {

        // ESSA LINHA IMPRIME O ERRO REAL (STACKTRACE) NO SEU TERMINAL!
        log.error("Ocorreu um erro inesperado no servidor: ", ex);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro interno inesperado no servidor."
        );

        problemDetail.setTitle("Erro Interno do Servidor");
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }

}

