package com.example.UBS.service;

import com.example.UBS.model.Paciente;
import com.example.UBS.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente salvar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente atualizar(Long id, Paciente novoPaciente) {
        return pacienteRepository.findById(id)
                .map(paciente -> {
                    paciente.setNome(novoPaciente.getNome());
                    paciente.setDataNascimento(novoPaciente.getDataNascimento());
                    paciente.setCpf(novoPaciente.getCpf());
                    paciente.setCartaoSUS(novoPaciente.getCartaoSUS());
                    paciente.setGenero(novoPaciente.getGenero());
                    paciente.setTelefone(novoPaciente.getTelefone());
                    paciente.setVacinas(novoPaciente.getVacinas());
                    paciente.setAlergias(novoPaciente.getAlergias());
                    return pacienteRepository.save(paciente);
                }).orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID: " + id));
    }

    public void deletar(Long id) {
        pacienteRepository.deleteById(id);
    }
}
