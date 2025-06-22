package com.example.UBS.repository;

import com.example.UBS.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    // Buscar por CPF
    Optional<Consulta> findByCpf(String cpf);
    boolean existsByCpf(String cpf);

    // Buscar por Cartão SUS
    Optional<Consulta> findByCartaoSUS(String cartaoSUS);
    boolean existsByCartaoSUS(String cartaoSUS);

    // Buscar por telefone
    Optional<Consulta> findByTelefone(String telefone);
    boolean existsByTelefone(String telefone);
}

