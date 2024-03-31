package com.tgid.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgid.teste.model.Empresa;
import com.tgid.teste.model.Taxa;

@Repository
public interface TaxaRepository extends JpaRepository<Taxa, Long> {

  Taxa findByEmpresa(Empresa empresa);


}
