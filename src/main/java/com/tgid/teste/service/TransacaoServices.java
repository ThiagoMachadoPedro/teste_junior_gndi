package com.tgid.teste.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgid.teste.model.Cliente;
import com.tgid.teste.model.Empresa;
import com.tgid.teste.model.Taxa;
import com.tgid.teste.model.Transacao;
import com.tgid.teste.repository.ClienteRepository;
import com.tgid.teste.repository.EmpresaRepository;
import com.tgid.teste.repository.TransacaoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TransacaoServices {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;


    // @Autowired
    // private MensagemService mensagemService;


    @Transactional
    public void transferirSaldoEmpresa(Long idRemetente, Long idDestinatario, BigDecimal valor) {
        Empresa remetente = empresaRepository.findById(idRemetente)
                .orElseThrow(() -> new EntityNotFoundException("Empresa remetente não encontrada"));

        Cliente destinatario = clienteRepository.findById(idDestinatario)
                .orElseThrow(() -> new EntityNotFoundException("Cliente destinatário não encontrado"));


        if (remetente.getSaldo().compareTo(valor) < 0) {
            throw new SaldoInsuficienteException("Saldo da empresa remetente é insuficiente");
        }

        // Obter a taxa da empresa remetente e calcular o valor da taxa
        BigDecimal valorLiquido = valor;
        for (Taxa taxa : remetente.getTaxas()) {
            BigDecimal porcentagemTaxa = taxa.getPorcentagem();
            BigDecimal valorTaxa = valorLiquido.multiply(porcentagemTaxa.divide(BigDecimal.valueOf(100)));

            // Deduzir a taxa do valor da transação
            valorLiquido = valorLiquido.subtract(valorTaxa);
        }

        // Atualizar os saldos da empresa remetente e do cliente destinatário
        BigDecimal novoSaldoRemetente = remetente.getSaldo().subtract(valor);
        BigDecimal novoSaldoDestinatario = destinatario.getSaldo().add(valorLiquido);

        remetente.setSaldo(novoSaldoRemetente);
        destinatario.setSaldo(novoSaldoDestinatario);

        // Salvar as alterações no banco de dados
        empresaRepository.save(remetente);
        clienteRepository.save(destinatario);

        // Criar e salvar a transação
        Transacao transacao = new Transacao();
        transacao.setValor(valorLiquido);
        transacao.setClienteOrigem(destinatario);
        transacao.setEmpresaDestino(remetente);
        transacao.setData(LocalDateTime.now());
        transacaoRepository.save(transacao);


        // // Enviar callback para a empresa
        // String assuntoEmpresa = "Transação Realizada";
        // String mensagemEmpresa = "Recebemos a transação no valor de " + valor + ".";
        // mensagemService.enviarEmail(remetente.getEmail(), assuntoEmpresa, mensagemEmpresa);

    }

    @Transactional
    public void transferirSaldoCliente(Long idClienteRemetente, Long idDestinatario, BigDecimal valor) {
        Cliente remetente = clienteRepository.findById(idClienteRemetente)
                .orElseThrow(() -> new EntityNotFoundException("Cliente remetente não encontrado"));

        Empresa destinatario = empresaRepository.findById(idDestinatario)
                .orElseThrow(() -> new EntityNotFoundException("Empresa destinatária não encontrada"));

        // Verifica se o cliente remetente tem saldo suficiente para a transferência
        if (remetente.getSaldo().compareTo(valor) < 0) {
            throw new SaldoInsuficienteException("Saldo do cliente remetente é insuficiente");
        }

        // Calcula o valor líquido da transação após aplicar as taxas
        BigDecimal valorLiquido = calcularValorLiquido(valor, remetente.getTaxas());

        // Atualiza os saldos do cliente remetente e da empresa destinatária
        BigDecimal novoSaldoRemetente = remetente.getSaldo().subtract(valor);
        BigDecimal novoSaldoDestinatario = destinatario.getSaldo().add(valorLiquido);

        remetente.setSaldo(novoSaldoRemetente);
        destinatario.setSaldo(novoSaldoDestinatario);

        // Salva as alterações no banco de dados
        clienteRepository.save(remetente);
        empresaRepository.save(destinatario);

        // Cria e salva a transação
        Transacao transacao = new Transacao();
        transacao.setValor(valorLiquido);
        transacao.setClienteOrigem(remetente);
        transacao.setEmpresaDestino(destinatario);
        transacao.setData(LocalDateTime.now());
        transacaoRepository.save(transacao);


        // // Enviar callback para a empresa
        // String assuntoEmpresa = "Transação Realizada";
        // String mensagemEmpresa = "Recebemos a transação no valor de " + valor + ".";
        // mensagemService.enviarEmail(remetente.getEmail(), assuntoEmpresa, mensagemEmpresa);



    }

    private BigDecimal calcularValorLiquido(BigDecimal valor, List<Taxa> taxas) {
        BigDecimal valorLiquido = valor;
        for (Taxa taxa : taxas) {
            BigDecimal porcentagemTaxa = taxa.getPorcentagem();
            BigDecimal valorTaxa = valorLiquido.multiply(porcentagemTaxa.divide(BigDecimal.valueOf(100)));

            // Deduzir a taxa do valor da transação
            valorLiquido = valorLiquido.subtract(valorTaxa);
        }
        return valorLiquido;
    }

}
