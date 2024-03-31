package com.tgid.teste.model;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Cliente extends EntidadeFinanceira {

  @CPF
  @Column(nullable = false)
  @NotNull(message = "CPF deve ser informado")
  @NotBlank(message = "CPF deve ser informado")
  private String cpf;

  @ManyToMany
  @JoinTable(name = "cliente_taxa", joinColumns = @JoinColumn(name = "cliente_id"), inverseJoinColumns = @JoinColumn(name = "taxa_id"))
  private List<Taxa> taxas;

  public Cliente() {
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public List<Taxa> getTaxas() {
    return taxas;
  }

  public void setTaxas(List<Taxa> taxas) {
    this.taxas = taxas;
  }

}
