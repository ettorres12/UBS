package com.example.UBS.service;

import com.example.UBS.model.Consulta;
import com.example.UBS.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GerenciadorConsultas {

    private final ConsultaRepository ConsultaRepository;

    public GerenciadorConsultas(ConsultaRepository ConsultaRepository) {
        this.ConsultaRepository = ConsultaRepository;
    }

    public boolean adicionarConsulta(String cartaoSUSConsulta, LocalDate dataHora, String cpfProfissional, String especialidadeConsulta,
                                     String local, String motivoConsulta, String observacoes, String diagnosticoCondicao) {
        try {
            Consulta novoConsulta = new Consulta(cartaoSUSConsulta, dataHora, cpfProfissional, especialidadeConsulta, local, 
                                                motivoConsulta, observacoes, diagnosticoCondicao);

            if (ConsultaRepository.existsBycpfProfissional(novoConsulta.getCpfProfissional())) {
                System.err.println("Consulta com CPF já existe.");
                return false;
            }
            if (ConsultaRepository.existsBycartaoSUSConsulta(novoConsulta.getCartaoSUSConsulta())) {
                System.err.println("Consulta com Cartão SUS já existe.");
                return false;
            }

            ConsultaRepository.save(novoConsulta);
            System.out.println("Consulta adicionado com sucesso.");
            return true;
        } catch (IllegalArgumentException e) {
            System.err.println("Erro nos dados do Consulta: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao adicionar Consulta: " + e.getMessage());
            return false;
        }
    }

    public Optional<Consulta> buscarConsultaPorCartaoSUS(String cartaoSUS) {
        if (cartaoSUS == null || cartaoSUS.trim().isEmpty()) {
            return Optional.empty();
        }
        String cartaoSUSNormalizado = cartaoSUS.replaceAll("[^0-9]", "");
        return ConsultaRepository.findBycartaoSUSConsulta(cartaoSUSNormalizado);
    }

    public Optional<Consulta> buscarConsultaPorCPF(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return Optional.empty();
        }
        String cpfNormalizado = cpf.replaceAll("[^0-9]", "");
        return ConsultaRepository.findBycpfProfissional(cpfNormalizado);
    }

    public List<Consulta> getTodosConsultas() {
        return ConsultaRepository.findAll();
    }

    public boolean atualizarConsulta(String cartaoSUSOriginal, String novoNome, LocalDate novaDataNascimento,
                                     String novoCPF, String novoCartaoSUS, String novoGenero, String novoTelefone,
                                     List<String> novasVacinas, List<String> novasAlergias) {
        if (cartaoSUSOriginal == null || cartaoSUSOriginal.trim().isEmpty()) {
            System.err.println("Cartão SUS original é obrigatório para atualização.");
            return false;
        }

        String cartaoSUSOriginalNormalizado = cartaoSUSOriginal.replaceAll("[^0-9]", "");
        Optional<Consulta> ConsultaExistenteOpt = ConsultaRepository.findBycartaoSUSConsulta(cartaoSUSOriginalNormalizado);
        if (ConsultaExistenteOpt.isEmpty()) {
            System.err.println("Consulta com Cartão SUS original não encontrado para atualização.");
            return false;
        }

        Consulta ConsultaExistente = ConsultaExistenteOpt.get();

        // Verifica se novo CPF ou novo CartaoSUS estão duplicados (se forem diferentes dos atuais)
        if (novoCPF != null && !novoCPF.equals(ConsultaExistente.getCpfProfissional()) && ConsultaRepository.existsBycpfProfissional(novoCPF)) {
            System.err.println("Novo CPF já existe para outro Consulta.");
            return false;
        }
        if (novoCartaoSUS != null && !novoCartaoSUS.equals(ConsultaExistente.getCartaoSUSConsulta()) && ConsultaRepository.existsBycartaoSUSConsulta(novoCartaoSUS)) {
            System.err.println("Novo Cartão SUS já existe para outro Consulta.");
            return false;
        }

        
   

        ConsultaRepository.save(ConsultaExistente);
        System.out.println("Consulta atualizado com sucesso.");
        return true;
    }

    public boolean removerConsultaPorCartaoSUS(String cartaoSUS) {
        if (cartaoSUS == null || cartaoSUS.trim().isEmpty()) {
            System.err.println("Cartão SUS inválido para remoção.");
            return false;
        }
        String cartaoSUSNormalizado = cartaoSUS.replaceAll("[^0-9]", "");
        Optional<Consulta> ConsultaOpt = ConsultaRepository.findBycartaoSUSConsulta(cartaoSUSNormalizado);
        if (ConsultaOpt.isEmpty()) {
            System.out.println("Nenhum Consulta encontrado com Cartão SUS " + cartaoSUS + " para remoção.");
            return false;
        }
        ConsultaRepository.delete(ConsultaOpt.get());
        System.out.println("Consulta removido com sucesso.");
        return true;
    }

    public boolean removerConsultaPorCPF(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            System.err.println("CPF inválido para remoção.");
            return false;
        }
        String cpfNormalizado = cpf.replaceAll("[^0-9]", "");
        Optional<Consulta> ConsultaOpt = ConsultaRepository.findBycpfProfissional(cpfNormalizado);
        if (ConsultaOpt.isEmpty()) {
            System.out.println("Nenhum Consulta encontrado com CPF " + cpf + " para remoção.");
            return false;
        }
        ConsultaRepository.delete(ConsultaOpt.get());
        System.out.println("Consulta removido com sucesso.");
        return true;
    }
}
