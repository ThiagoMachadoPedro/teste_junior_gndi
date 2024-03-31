package com.tgid.teste.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgid.teste.model.Cliente;
import com.tgid.teste.service.ClienteServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

  @Autowired
  private ClienteServices clienteService;

  @GetMapping
  public List<Cliente> listarClientes() {
    return clienteService.listarClientes();
  }

  @PostMapping
  public ResponseEntity<Cliente> criarCliente(@RequestBody @Valid Cliente cliente) {
    Cliente clienteSalvo = clienteService.criarCliente(cliente, cliente.getTaxas());
    return ResponseEntity.ok(clienteSalvo);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
    Optional<Cliente> cliente = clienteService.buscarClientePorId(id);
    return cliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
    clienteService.deletarCliente(id);
    return ResponseEntity.noContent().build();
  }

}
