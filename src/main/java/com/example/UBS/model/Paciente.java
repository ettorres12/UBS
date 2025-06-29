package com.example.UBS.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Entity
public class Paciente {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String cartaoSUS;
    private String genero;
    private String telefone;

    @ElementCollection
    private List<String> vacinas = new ArrayList<>();

    @ElementCollection
    private List<String> alergias = new ArrayList<>();

    public Paciente() {
    }

    public Paciente(String nome, LocalDate dataNascimento, String cpf, String cartaoSUS,
                    String genero, String telefone, List<String> vacinas, List<String> alergias) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.setCpf(cpf);
        this.setCartaoSUS(cartaoSUS);
        this.genero = genero;
        this.telefone = telefone;
        this.vacinas = (vacinas != null) ? new ArrayList<>(vacinas) : new ArrayList<>();
        this.alergias = (alergias != null) ? new ArrayList<>(alergias) : new ArrayList<>();
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

    public String getCartaoSUS() {
        return cartaoSUS;
    }

    public void setCartaoSUS(String cartaoSUS) {
        if (cartaoSUS == null || !Pattern.matches("\\d{15}", cartaoSUS)) {
            throw new IllegalArgumentException("Cartão SUS inválido. Deve conter 15 dígitos numéricos.");
        }
        this.cartaoSUS = cartaoSUS;
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

    public List<String> getVacinas() {
        return vacinas;
    }

    public void setVacinas(List<String> vacinas) {
        this.vacinas = (vacinas != null) ? new ArrayList<>(vacinas) : new ArrayList<>();
    }

    public List<String> getAlergias() {
        return alergias;
    }

    public void setAlergias(List<String> alergias) {
        this.alergias = (alergias != null) ? new ArrayList<>(alergias) : new ArrayList<>();
    }

    public int getIdade() {
        if (this.dataNascimento == null) return 0;
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }
}
