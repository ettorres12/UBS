package com.example.UBS.service;

import com.example.UBS.model.Paciente;
import com.example.UBS.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GerenciadorPacientes {
  
    private final PacienteRepository pacienteRepository;

    // Injeção via construtor
    public GerenciadorPacientes(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public boolean adicionarPaciente(String nome, LocalDate dataNascimento, String cpf, String cartaoSUS,
                                     String genero, String telefone, List<String> vacinas, List<String> alergias) {
        try {
            Paciente novoPaciente = new Paciente(nome, dataNascimento, cpf, cartaoSUS, genero, telefone,
                    vacinas != null ? vacinas : new ArrayList<>(),
                    alergias != null ? alergias : new ArrayList<>());

            // Aqui podemos fazer verificações se já existe paciente com CPF ou CartaoSUS
            if (pacienteRepository.existsByCpf(novoPaciente.getCpf())) {
                System.err.println("Paciente com CPF já existe.");
                return false;
            }
            if (pacienteRepository.existsByCartaoSUS(novoPaciente.getCartaoSUS())) {
                System.err.println("Paciente com Cartão SUS já existe.");
                return false;
            }

            pacienteRepository.save(novoPaciente);
            System.out.println("Paciente adicionado com sucesso.");
            return true;
        } catch (IllegalArgumentException e) {
            System.err.println("Erro nos dados do paciente: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao adicionar paciente: " + e.getMessage());
            return false;
        }
    }

    public Optional<Paciente> buscarPacientePorCartaoSUS(String cartaoSUS) {
        if (cartaoSUS == null || cartaoSUS.trim().isEmpty()) {
            return Optional.empty();
        }
        String cartaoSUSNormalizado = cartaoSUS.replaceAll("[^0-9]", "");
        return pacienteRepository.findByCartaoSUS(cartaoSUSNormalizado);
    }

    public Optional<Paciente> buscarPacientePorCPF(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return Optional.empty();
        }
        String cpfNormalizado = cpf.replaceAll("[^0-9]", "");
        return pacienteRepository.findByCpf(cpfNormalizado);
    }

    public List<Paciente> getTodosPacientes() {
        return pacienteRepository.findAll();
    }

    public boolean atualizarPaciente(String cartaoSUSOriginal, String novoNome, LocalDate novaDataNascimento,
                                     String novoCPF, String novoCartaoSUS, String novoGenero, String novoTelefone,
                                     List<String> novasVacinas, List<String> novasAlergias) {
        if (cartaoSUSOriginal == null || cartaoSUSOriginal.trim().isEmpty()) {
            System.err.println("Cartão SUS original é obrigatório para atualização.");
            return false;
        }

        String cartaoSUSOriginalNormalizado = cartaoSUSOriginal.replaceAll("[^0-9]", "");
        Optional<Paciente> pacienteExistenteOpt = pacienteRepository.findByCartaoSUS(cartaoSUSOriginalNormalizado);
        if (pacienteExistenteOpt.isEmpty()) {
            System.err.println("Paciente com Cartão SUS original não encontrado para atualização.");
            return false;
        }

        Paciente pacienteExistente = pacienteExistenteOpt.get();

        // Verifica se novo CPF ou novo CartaoSUS estão duplicados (se forem diferentes dos atuais)
        if (novoCPF != null && !novoCPF.equals(pacienteExistente.getCpf()) && pacienteRepository.existsByCpf(novoCPF)) {
            System.err.println("Novo CPF já existe para outro paciente.");
            return false;
        }
        if (novoCartaoSUS != null && !novoCartaoSUS.equals(pacienteExistente.getCartaoSUS()) && pacienteRepository.existsByCartaoSUS(novoCartaoSUS)) {
            System.err.println("Novo Cartão SUS já existe para outro paciente.");
            return false;
        }

        // Atualiza campos
        pacienteExistente.setNome(novoNome);
        pacienteExistente.setDataNascimento(novaDataNascimento);
        pacienteExistente.setCpf(novoCPF);
        pacienteExistente.setCartaoSUS(novoCartaoSUS);
        pacienteExistente.setGenero(novoGenero);
        pacienteExistente.setTelefone(novoTelefone);
        pacienteExistente.setVacinas(novasVacinas != null ? novasVacinas : new ArrayList<>());
        pacienteExistente.setAlergias(novasAlergias != null ? novasAlergias : new ArrayList<>());

        pacienteRepository.save(pacienteExistente);
        System.out.println("Paciente atualizado com sucesso.");
        return true;
    }

    public boolean removerPacientePorCartaoSUS(String cartaoSUS) {
        if (cartaoSUS == null || cartaoSUS.trim().isEmpty()) {
            System.err.println("Cartão SUS inválido para remoção.");
            return false;
        }
        String cartaoSUSNormalizado = cartaoSUS.replaceAll("[^0-9]", "");
        Optional<Paciente> pacienteOpt = pacienteRepository.findByCartaoSUS(cartaoSUSNormalizado);
        if (pacienteOpt.isEmpty()) {
            System.out.println("Nenhum paciente encontrado com Cartão SUS " + cartaoSUS + " para remoção.");
            return false;
        }
        pacienteRepository.delete(pacienteOpt.get());
        System.out.println("Paciente removido com sucesso.");
        return true;
    }

    public boolean removerPacientePorCPF(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            System.err.println("CPF inválido para remoção.");
            return false;
        }
        String cpfNormalizado = cpf.replaceAll("[^0-9]", "");
        Optional<Paciente> pacienteOpt = pacienteRepository.findByCpf(cpfNormalizado);
        if (pacienteOpt.isEmpty()) {
            System.out.println("Nenhum paciente encontrado com CPF " + cpf + " para remoção.");
            return false;
        }
        pacienteRepository.delete(pacienteOpt.get());
        System.out.println("Paciente removido com sucesso.");
        return true;
    }
}
