package com.tgid.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgid.teste.model.DTO.TransferenciaDTO;
import com.tgid.teste.service.TransacaoServices;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/tranferencias")
public class TranferenciaController {


    @Autowired
  private TransacaoServices transacaoServices;

  @PostMapping("/empresa")
  public ResponseEntity<String> transferirSaldoEmpreClien(@RequestParam @Valid Long idRemetente,
      @RequestParam Long idDestinatario,
      @RequestBody TransferenciaDTO request) {
    try {
      transacaoServices.transferirSaldoEmpresa(idRemetente, idDestinatario, request.getValor());
      return ResponseEntity.ok("Transferência realizada com sucesso");
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
    }
  }



  @PostMapping("/cliente")
  public ResponseEntity<String> transferirSaldoClienEmpre(@RequestParam @Valid Long idRemetente,
      @RequestParam Long idDestinatario,
      @RequestBody TransferenciaDTO request) {
    try {
      transacaoServices.transferirSaldoCliente(idRemetente, idDestinatario, request.getValor());
      return ResponseEntity.ok("Transferência realizada com sucesso");
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
    }
  }


}
