package com.tgid.teste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgid.teste.model.Empresa;
import com.tgid.teste.model.Taxa;
import com.tgid.teste.repository.EmpresaRepository;
import com.tgid.teste.repository.TaxaRepository;

import jakarta.transaction.Transactional;

@Service
public class EmpresaServices {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private TaxaRepository taxaRepository;

    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    @Transactional
    public Empresa criarEmpresa(Empresa empresa) {
        // Salva a empresa para obter o ID gerado
        Empresa empresaSalva = empresaRepository.save(empresa);

        // Associa cada taxa à empresa
        List<Taxa> taxas = empresa.getTaxas();
        for (Taxa taxa : taxas) {
            taxa.setEmpresa(empresaSalva);
            taxaRepository.save(taxa);
        }

        // Retorna a empresa com as taxas associadas
        return empresaSalva;
    }

    public Optional<Empresa> buscarEmpresaPorId(Long id) {
        return empresaRepository.findById(id);
    }

    public void deletarEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }

}
