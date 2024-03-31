package com.tgid.teste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgid.teste.model.Taxa;
import com.tgid.teste.repository.TaxaRepository;

@Service
public class TaxaServices {

  @Autowired
  private TaxaRepository taxaRepository;

  public List<Taxa> listarTaxas() {
    return taxaRepository.findAll();
  }

  public Taxa criarTaxa(Taxa Taxa) {
    return taxaRepository.save(Taxa);
  }

  public Optional<Taxa> buscarTaxaPorId(Long id) {
    return taxaRepository.findById(id);
  }

  public void deletarTaxa(Long id) {
    taxaRepository.deleteById(id);
  }

}
