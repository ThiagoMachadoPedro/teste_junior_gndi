package com.tgid.teste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgid.teste.model.Empresa;

@Repository
public interface EmpresaRepository  extends JpaRepository<Empresa, Long>{

  public Optional<Empresa> findByNumeroConta(String numeroConta);

}
