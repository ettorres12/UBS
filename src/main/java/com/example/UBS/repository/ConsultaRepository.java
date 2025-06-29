package com.example.UBS.repository;

import com.example.UBS.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    // Buscar por CPF
    Optional<Consulta> findBycpfProfissional(String cpfProfissional);
    boolean existsBycpfProfissional(String cpfProfissional);

    // Buscar por Cartão SUS
    Optional<Consulta> findBycartaoSUSConsulta(String cartaoSUSConsulta);
    boolean existsBycartaoSUSConsulta(String cartaoSUSConsulta);
  
}

