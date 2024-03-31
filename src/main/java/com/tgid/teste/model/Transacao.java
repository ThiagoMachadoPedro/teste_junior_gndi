package com.tgid.teste.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.ForeignKey;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transacao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private BigDecimal valor;

  @ManyToOne
  @JoinColumn(name = "cliente_origem_id", nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "cliente_origem_id_fk"))
  private Cliente clienteOrigem;
 
  @ManyToOne
  @JoinColumn(name = "empresa_destino_id", nullable = true,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_destino_id_fk"))
  private Empresa empresaDestino;

  private LocalDateTime data;

  public Transacao() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  public Cliente getClienteOrigem() {
    return clienteOrigem;
  }

  public void setClienteOrigem(Cliente clienteOrigem) {
    this.clienteOrigem = clienteOrigem;
  }

  public Empresa getEmpresaDestino() {
    return empresaDestino;
  }

  public void setEmpresaDestino(Empresa empresaDestino) {
    this.empresaDestino = empresaDestino;
  }

  public LocalDateTime getData() {
    return data;
  }

  public void setData(LocalDateTime data) {
    this.data = data;
  }

  public Transacao(Long id, BigDecimal valor, Cliente clienteOrigem, Empresa empresaDestino, LocalDateTime data) {
    this.id = id;
    this.valor = valor;
    this.clienteOrigem = clienteOrigem;
    this.empresaDestino = empresaDestino;
    this.data = data;
  }

}
