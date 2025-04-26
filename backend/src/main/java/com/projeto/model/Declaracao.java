package com.projeto.model;

import java.time.LocalDate;

public class Declaracao {

    private Long idDesclaracao;
    private Paciente paciente;
    private Medico medico;
    private LocalDate dataEmissao;
    private String descricao;


    public Declaracao(Long idDesclaracao, Paciente paciente, Medico medico, LocalDate dataEmissao, String descricao) {
        this.idDesclaracao = idDesclaracao;
        this.paciente = paciente;
        this.medico = medico;
        this.dataEmissao = dataEmissao;
        this.descricao = descricao;
    }

    public Long getIdDesclaracao() {
        return idDesclaracao;
    }

    public void setIdDesclaracao(Long idDesclaracao) {
        this.idDesclaracao = idDesclaracao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
