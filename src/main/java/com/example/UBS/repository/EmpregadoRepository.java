package com.example.UBS.repository;

import com.example.UBS.model.Empregado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

    // Buscar por CPF
    Optional<Empregado> findByCpf(String cpf);
    boolean existsByCpf(String cpf);

    // Buscar por telefone
    Optional<Empregado> findByTelefone(String telefone);
    boolean existsByTelefone(String telefone);
}

