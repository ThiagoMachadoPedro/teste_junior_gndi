package com.tgid.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.tgid.teste.model.Taxa;
import com.tgid.teste.service.TaxaServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/taxas")
public class TaxaController {

  @Autowired
  private TaxaServices taxaService;

  @GetMapping
  public List<Taxa> listarTaxas() {
    return taxaService.listarTaxas();
  }

  @PostMapping
  public ResponseEntity<Taxa> criarTaxa(@RequestBody @Valid Taxa Taxa) {
    Taxa novoTaxa = taxaService.criarTaxa(Taxa);
    return ResponseEntity.status(HttpStatus.CREATED).body(novoTaxa);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Taxa> buscarTaxaPorId(@PathVariable Long id) {
    Optional<Taxa> Taxa = taxaService.buscarTaxaPorId(id);
    return Taxa.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarTaxa(@PathVariable Long id) {
    taxaService.deletarTaxa(id);
    return ResponseEntity.noContent().build();
  }


}
