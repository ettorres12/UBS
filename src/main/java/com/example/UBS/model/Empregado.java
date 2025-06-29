package com.example.UBS.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

@Entity
public class Empregado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String genero;
    private String telefone;
    private String registro;
    private String funcao;

    public Empregado() {
    }

    public Empregado(String nome, LocalDate dataNascimento, String cpf,
                    String genero, String telefone, String registro, String funcao) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.setCpf(cpf);
        this.genero = genero;
        this.telefone = telefone;
        this.registro = registro;
        this.funcao = funcao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || !Pattern.matches("\\d{11}", cpf)) {
            throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos numéricos.");
        }
        this.cpf = cpf;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public int getIdade() {
        if (this.dataNascimento == null) return 0;
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }
}
