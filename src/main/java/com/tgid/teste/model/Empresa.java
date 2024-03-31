package com.tgid.teste.model;

import java.util.List;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Empresa extends EntidadeFinanceira {


  @CNPJ
  @Column(nullable = false)
  @NotNull(message = "CNPJ deve ser informado")
  @NotBlank(message = "CNPJ deve ser informado")
  private String cnpj;

  @OneToMany(mappedBy = "empresa" , orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Taxa> taxas;

  public Empresa() {
  }

  // public void depositarSaldo(BigDecimal valor) {
  // this.saldo = this.saldo.add(valor);
  // }


  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public List<Taxa> getTaxas() {
    return taxas;
  }

  public void setTaxas(List<Taxa> taxas) {
    this.taxas = taxas;
  }

}
