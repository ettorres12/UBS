package com.example.UBS.service;

import com.example.UBS.model.Empregado;
import com.example.UBS.repository.EmpregadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpregadoService {

    @Autowired
    private EmpregadoRepository EmpregadoRepository;

    public List<Empregado> listarTodos() {
        return EmpregadoRepository.findAll();
    }

    public Optional<Empregado> buscarPorId(Long id) {
        return EmpregadoRepository.findById(id);
    }

    public Empregado salvar(Empregado Empregado) {
        return EmpregadoRepository.save(Empregado);
    }

    public Empregado atualizar(Long id, Empregado novoEmpregado) {
        return EmpregadoRepository.findById(id)
                .map(Empregado -> {
                    Empregado.setNome(novoEmpregado.getNome());
                    Empregado.setDataNascimento(novoEmpregado.getDataNascimento());
                    Empregado.setCpf(novoEmpregado.getCpf());
                    Empregado.setGenero(novoEmpregado.getGenero());
                    Empregado.setTelefone(novoEmpregado.getTelefone());
                    Empregado.setRegistro(novoEmpregado.getRegistro());
                    return EmpregadoRepository.save(Empregado);
                }).orElseThrow(() -> new RuntimeException("Empregado não encontrado com ID: " + id));
    }

    public void deletar(Long id) {
        EmpregadoRepository.deleteById(id);
    }
}
