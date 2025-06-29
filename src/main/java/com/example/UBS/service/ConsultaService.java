package com.example.UBS.service;

import com.example.UBS.model.Consulta;
import com.example.UBS.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository ConsultaRepository;
  
    public List<Consulta> listarTodos() {
        return ConsultaRepository.findAll();
    }

    public Optional<Consulta> buscarPorId(Long id) {
        return ConsultaRepository.findById(id);
    }

    public Consulta salvar(Consulta Consulta) {
        return ConsultaRepository.save(Consulta);
    }

    public Consulta atualizar(Long id, Consulta novoConsulta) {
        return ConsultaRepository.findById(id)
                .map(Consulta -> {
                    Consulta.setDataHora(novoConsulta.getDataHora());
                    Consulta.setCpfProfissional(novoConsulta.getCpfProfissional());
                    Consulta.setCartaoSUSConsulta(novoConsulta.getCartaoSUSConsulta());
                    Consulta.setLocal(novoConsulta.getLocal());
                    Consulta.setMotivoConsulta(novoConsulta.getMotivoConsulta());
                    Consulta.setEspecialidadeConsulta(novoConsulta.getEspecialidadeConsulta());
                    Consulta.setObservacoes(novoConsulta.getObservacoes());
                    Consulta.setDiagnosticoCondicao(novoConsulta.getDiagnosticoCondicao());
                    return ConsultaRepository.save(Consulta);
                }).orElseThrow(() -> new RuntimeException("Consulta não encontrado com ID: " + id));
    }

    public void deletar(Long id) {
        ConsultaRepository.deleteById(id);
    }
}
