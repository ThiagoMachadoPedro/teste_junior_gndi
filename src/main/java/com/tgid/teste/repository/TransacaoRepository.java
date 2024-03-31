package com.tgid.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgid.teste.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao,Long> {

}
