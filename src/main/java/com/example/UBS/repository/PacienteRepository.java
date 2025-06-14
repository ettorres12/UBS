package com.example.UBS.repository;

import com.example.UBS.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // Buscar por CPF
    Optional<Paciente> findByCpf(String cpf);
    boolean existsByCpf(String cpf);

    // Buscar por Cartão SUS
    Optional<Paciente> findByCartaoSUS(String cartaoSUS);
    boolean existsByCartaoSUS(String cartaoSUS);

    // Buscar por telefone
    Optional<Paciente> findByTelefone(String telefone);
    boolean existsByTelefone(String telefone);
}

