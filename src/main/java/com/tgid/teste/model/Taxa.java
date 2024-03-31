package com.tgid.teste.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Taxa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @NotBlank(message = "Nome deve ser informado")
  @NotNull(message = "Nome deve ser informado")
  @Column(nullable = false)
  private String nome;

  @ManyToOne
  @JsonIgnore
  private Empresa empresa;

  private BigDecimal porcentagem;



  public Taxa() {
  }



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



  public BigDecimal getPorcentagem() {
    return porcentagem;
  }



  public void setPorcentagem(BigDecimal porcentagem) {
    this.porcentagem = porcentagem;
  }



  public Empresa getEmpresa() {
    return empresa;
  }



  public void setEmpresa(Empresa empresa) {
    this.empresa = empresa;
  }




}
