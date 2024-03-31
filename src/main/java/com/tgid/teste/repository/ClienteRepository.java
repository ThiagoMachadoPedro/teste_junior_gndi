package com.tgid.teste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgid.teste.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  public Optional<Cliente> findByNumeroConta(String numeroConta);

}
