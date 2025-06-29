package com.example.UBS.service;

import com.example.UBS.model.Empregado;
import com.example.UBS.repository.EmpregadoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GerenciadorEmpregados {
  
    private final EmpregadoRepository EmpregadoRepository;

    // Injeção via construtor
    public GerenciadorEmpregados(EmpregadoRepository EmpregadoRepository) {
        this.EmpregadoRepository = EmpregadoRepository;
    }

    public boolean adicionarEmpregado(String nome, LocalDate dataNascimento, String cpf,
                                     String genero, String telefone, String registro, String funcao) {
        try {
            Empregado novoEmpregado = new Empregado(nome, dataNascimento, cpf, genero, telefone, registro, funcao);

            // Aqui podemos fazer verificações se já existe Empregado com CPF ou Registro
            if (EmpregadoRepository.existsByCpf(novoEmpregado.getCpf())) {
                System.err.println("Empregado com CPF já existe.");
                return false;
            }
            if (EmpregadoRepository.existsByRegistro(novoEmpregado.getRegistro())) {
                System.err.println("Empregado com registro já existe.");
                return false;
            }

            EmpregadoRepository.save(novoEmpregado);
            System.out.println("Empregado adicionado com sucesso.");
            return true;
        } catch (IllegalArgumentException e) {
            System.err.println("Erro nos dados do Empregado: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao adicionar Empregado: " + e.getMessage());
            return false;
        }
    }

 public Optional<Empregado> buscarEmpregadoPorRegistro(String registro) {
        if (registro == null || registro.trim().isEmpty()) {
            return Optional.empty();
        }
        String registroNormalizado = registro.replaceAll("[^0-9]", "");
        return EmpregadoRepository.findByRegistro(registroNormalizado);
    }

    public Optional<Empregado> buscarEmpregadoPorCPF(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return Optional.empty();
        }
        String cpfNormalizado = cpf.replaceAll("[^0-9]", "");
        return EmpregadoRepository.findByCpf(cpfNormalizado);
    }

    public List<Empregado> getTodosEmpregados() {
        return EmpregadoRepository.findAll();
    }

    public boolean atualizarEmpregado(String registroOriginal, String novoNome, LocalDate novaDataNascimento,
                                     String novoCPF, String novoRegistro, String novoGenero, String novoTelefone, String novoFuncao) {
        if (registroOriginal == null || registroOriginal.trim().isEmpty()) {
            System.err.println("registro original é obrigatório para atualização.");
            return false;
        }

        String registroOriginalNormalizado = registroOriginal.replaceAll("[^0-9]", "");
        Optional<Empregado> EmpregadoExistenteOpt = EmpregadoRepository.findByRegistro(registroOriginalNormalizado);
        if (EmpregadoExistenteOpt.isEmpty()) {
            System.err.println("Empregado com registro original não encontrado para atualização.");
            return false;
        }

        Empregado EmpregadoExistente = EmpregadoExistenteOpt.get();

        // Verifica se novo CPF ou novo registro estão duplicados (se forem diferentes dos atuais)
        if (novoCPF != null && !novoCPF.equals(EmpregadoExistente.getCpf()) && EmpregadoRepository.existsByCpf(novoCPF)) {
            System.err.println("Novo CPF já existe para outro Empregado.");
            return false;
        }
        if (novoRegistro != null && !novoRegistro.equals(EmpregadoExistente.getRegistro()) && EmpregadoRepository.existsByRegistro(novoRegistro)) {
            System.err.println("Novo regisrto já existe para outro Empregado.");
            return false;
        }

        // Atualiza campos
        EmpregadoExistente.setNome(novoNome);
        EmpregadoExistente.setDataNascimento(novaDataNascimento);
        EmpregadoExistente.setCpf(novoCPF);
        EmpregadoExistente.setRegistro(novoRegistro);
        EmpregadoExistente.setGenero(novoGenero);
        EmpregadoExistente.setTelefone(novoTelefone);
        EmpregadoExistente.setFuncao(novoFuncao);

        EmpregadoRepository.save(EmpregadoExistente);
        System.out.println("Empregado atualizado com sucesso.");
        return true;
    }

    public boolean removerEmpregadoPorRegistro(String registro) {
        if (registro == null || registro.trim().isEmpty()) {
            System.err.println("Registro inválido para remoção.");
            return false;
        }
        String registroNormalizado = registro.replaceAll("[^0-9]", "");
        Optional<Empregado> EmpregadoOpt = EmpregadoRepository.findByRegistro(registroNormalizado);
        if (EmpregadoOpt.isEmpty()) {
            System.out.println("Nenhum Empregado encontrado com registro " + registro + " para remoção.");
            return false;
        }
        EmpregadoRepository.delete(EmpregadoOpt.get());
        System.out.println("Empregado removido com sucesso.");
        return true;
    }

    public boolean removerEmpregadoPorCPF(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            System.err.println("CPF inválido para remoção.");
            return false;
        }
        String cpfNormalizado = cpf.replaceAll("[^0-9]", "");
        Optional<Empregado> EmpregadoOpt = EmpregadoRepository.findByCpf(cpfNormalizado);
        if (EmpregadoOpt.isEmpty()) {
            System.out.println("Nenhum Empregado encontrado com CPF " + cpf + " para remoção.");
            return false;
        }
        EmpregadoRepository.delete(EmpregadoOpt.get());
        System.out.println("Empregado removido com sucesso.");
        return true;
    }
}
