package com.tgid.teste.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgid.teste.model.Cliente;
import com.tgid.teste.model.Taxa;
import com.tgid.teste.repository.ClienteRepository;
import com.tgid.teste.repository.TaxaRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteServices {

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private TaxaRepository taxaRepository;

  public List<Cliente> listarClientes() {
    return clienteRepository.findAll();
  }

  @Transactional
public Cliente criarCliente(Cliente cliente, List<Taxa> taxas) {
    List<Taxa> taxasSalvas = new ArrayList<>();
    for (Taxa taxa : taxas) {
        taxasSalvas.add(taxaRepository.save(taxa));
    }

    cliente.setTaxas(taxasSalvas);
    return clienteRepository.save(cliente);
}


  public Optional<Cliente> buscarClientePorId(Long id) {
    return clienteRepository.findById(id);
  }

  public void deletarCliente(Long id) {
    clienteRepository.deleteById(id);
  }

}
