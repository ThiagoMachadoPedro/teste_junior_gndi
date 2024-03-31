package com.tgid.teste.component;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionGlobal extends ResponseEntityExceptionHandler {


  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

    if (ex.getCause() instanceof MethodArgumentNotValidException) {
      MethodArgumentNotValidException manve = (MethodArgumentNotValidException) ex.getCause();

      Map<String, Object> body = new LinkedHashMap<>();
      body.put("timestamp", LocalDateTime.now());
      body.put("status", status.value());

      // Obter todos os erros de validação
      List<String> errors = manve.getBindingResult()
          .getFieldErrors()
          .stream()
          .map(FieldError::getDefaultMessage)
          .collect(Collectors.toList());

      body.put("errors", errors);

      return new ResponseEntity<>(body, headers, status);
    }

    return ResponseEntity.status(status).headers(headers).body("Requisição inválida");
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.BAD_REQUEST.value());

    // Obter todos os erros de validação
    List<String> errors = ex.getConstraintViolations()
        .stream()
        .map(ConstraintViolation::getMessage)
        .collect(Collectors.toList());

    body.put("errors", errors);

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

  
}
