package com.tgid.teste.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class EntidadeFinanceira implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id;

  @Size(min = 4, message = "O nome deve ter no minimo 4 letras")
  @NotBlank(message = "Nome deve ser informado")
  @NotNull(message = "Nome deve ser informado")
  @Column(nullable = false)
  private String nome;

  @Email
  @Column(nullable = false)
  @NotNull(message = "Email deve ser informado")
  @NotBlank(message = "Email deve ser informado")
  private String email;

  @Column(nullable = false)
  @NotNull(message = "Email deve ser informado")
  private String numeroConta;


  @Column(nullable = false)
  @NotNull(message = "Saldo deve ser informado")
  private BigDecimal saldo;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getNumeroConta() {
    return numeroConta;
  }

  public void setNumeroConta(String numeroConta) {
    this.numeroConta = numeroConta;
  }

  public BigDecimal getSaldo() {
    return saldo;
  }

  public void setSaldo(BigDecimal saldo) {
    this.saldo = saldo;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
