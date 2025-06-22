package com.example.UBS.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idConsulta;
    private String cartaoSUSConsulta;
    // private String crmMedico; // REMOVIDO
    private String cpfProfissional; // ADICIONADO
    private String local;
    private LocalDateTime dataHora;
    private String motivoConsulta;
    private String especialidadeConsulta;
    private String observacoes;
    private String diagnosticoCondicao;

    public Consulta(String cartaoSUSConsulta, String cpfProfissional, String local, LocalDateTime dataHora,
                    String motivoConsulta, String especialidadeConsulta, String observacoes,
                    String diagnosticoCondicao) {
        this.idConsulta = UUID.randomUUID().toString(); //
        this.cartaoSUSConsulta = cartaoSUSConsulta; //
        this.cpfProfissional = cpfProfissional; // ATUALIZADO
        this.local = local; //
        this.dataHora = dataHora; //
        this.motivoConsulta = motivoConsulta; //
        this.especialidadeConsulta = especialidadeConsulta; //
        this.observacoes = observacoes; //
        this.diagnosticoCondicao = diagnosticoCondicao; //
   
    }

     // Construtor para carregar do DAO
    public Consulta(String idConsulta, String cartaoSUSConsulta, String cpfProfissional, String local, LocalDateTime dataHora,
                    String motivoConsulta, String especialidadeConsulta, String observacoes,
                    String diagnosticoCondicao) {
        this.idConsulta = idConsulta; //
        this.cartaoSUSConsulta = cartaoSUSConsulta; //
        this.cpfProfissional = cpfProfissional; // ATUALIZADO
        this.local = local; //
        this.dataHora = dataHora; //
        this.motivoConsulta = motivoConsulta; //
        this.especialidadeConsulta = especialidadeConsulta; //
        this.observacoes = observacoes; //
        this.diagnosticoCondicao = diagnosticoCondicao; //
        //this.itensPrescricao = (itensPrescricao != null) ? new ArrayList<>(itensPrescricao) : new ArrayList<>();
    }

    public Consulta(String cartaoSUSConsulta2, LocalDate dataHora2, String cpfProfissional2,
            String especialidadeConsulta2, String local2, String motivoConsulta2, String observacoes2,
            String diagnosticoCondicao2) {
        //TODO Auto-generated constructor stub
    }

    // Getters e Setters
    public String getIdConsulta() { return idConsulta; } //
    public String getCartaoSUSConsulta() { return cartaoSUSConsulta; } //
    public void setCartaoSUSConsulta(String cartaoSUSConsulta) { this.cartaoSUSConsulta = cartaoSUSConsulta; } //

    // Getter e Setter para o novo campo
    public String getCpfProfissional() { return cpfProfissional; }
    public void setCpfProfissional(String cpfProfissional) { this.cpfProfissional = cpfProfissional; }

    // getCrmMedico() e setCrmMedico() foram removidos

    public String getLocal() { return local; } //
    public void setLocal(String local) { this.local = local; } //
    public LocalDateTime getDataHora() { return dataHora; } //
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; } //
    public String getMotivoConsulta() { return motivoConsulta; } //
    public void setMotivoConsulta(String motivoConsulta) { this.motivoConsulta = motivoConsulta; } //
    public String getEspecialidadeConsulta() { return especialidadeConsulta; } //
    public void setEspecialidadeConsulta(String especialidadeConsulta) { this.especialidadeConsulta = especialidadeConsulta; } //
    public String getObservacoes() { return observacoes; } //
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; } //
    public String getDiagnosticoCondicao() { return diagnosticoCondicao; } //
    public void setDiagnosticoCondicao(String diagnosticoCondicao) { this.diagnosticoCondicao = diagnosticoCondicao; } //

    

    @Override
    public String toString() { // toString ATUALIZADO
        return "Consulta {" +
                "idConsulta='" + idConsulta + '\'' +
                ", cartaoSUSConsulta='" + cartaoSUSConsulta + '\'' +
                ", cpfProfissional='" + cpfProfissional + '\'' + // ATUALIZADO
                ", local='" + local + '\'' +
                ", dataHora=" + dataHora +
                // ... resto dos campos
                '}';
    }
}