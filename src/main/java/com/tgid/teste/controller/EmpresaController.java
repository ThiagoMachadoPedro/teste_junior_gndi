package com.tgid.teste.controller;

import java.util.List;
import java.util.Optional;

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

import com.tgid.teste.model.Empresa;
import com.tgid.teste.service.EmpresaServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

  @Autowired
  private EmpresaServices empresaService;

  @GetMapping
  public List<Empresa> listarEmpresas() {
    return empresaService.listarEmpresas();
  }

  @PostMapping
  public ResponseEntity<Empresa> criarEmpresa(@RequestBody @Valid Empresa empresa) {
    Empresa novaEmpresa = empresaService.criarEmpresa(empresa);
    return ResponseEntity.status(HttpStatus.CREATED).body(novaEmpresa);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Empresa> buscarEmpresaPorId(@PathVariable Long id) {
    Optional<Empresa> empresa = empresaService.buscarEmpresaPorId(id);
    return empresa.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarEmpresa(@PathVariable Long id) {
    empresaService.deletarEmpresa(id);
    return ResponseEntity.noContent().build();
  }

}
