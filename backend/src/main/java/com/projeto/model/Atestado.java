package com.projeto.model;

import java.time.LocalDate;

public class Atestado {

    private Long idAtestado;
    private Paciente paciente;
    private Medico medico;
    private LocalDate dataEmissao;
    private LocalDate dataValidade;
    private String descricao;

    public Atestado(Long idAtestado, Paciente paciente, Medico medico, LocalDate dataEmissao, LocalDate dataValidade, String descricao) {
        this.idAtestado = idAtestado;
        this.paciente = paciente;
        this.medico = medico;
        this.dataEmissao = dataEmissao;
        this.dataValidade = dataValidade;
        this.descricao = descricao;
    }

    public Long getIdAtestado() {
        return idAtestado;
    }

    public void setIdAtestado(Long idAtestado) {
        this.idAtestado = idAtestado;
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

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
