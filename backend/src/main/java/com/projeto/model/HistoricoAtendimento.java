package com.projeto.model;

import java.time.LocalDate;

public class HistoricoAtendimento {

    private Long idHistoricoAtendimento;
    private Paciente paciente;
    private Medico medico;
    private String descricao;
    private LocalDate dataAtendimento;

    public HistoricoAtendimento(Long idHistoricoAtendimento, Paciente paciente, Medico medico, String descricao, LocalDate dataAtendimento) {
        this.idHistoricoAtendimento = idHistoricoAtendimento;
        this.paciente = paciente;
        this.medico = medico;
        this.descricao = descricao;
        this.dataAtendimento = dataAtendimento;
    }

    public Long getIdHistoricoAtendimento() {
        return idHistoricoAtendimento;
    }

    public void setIdHistoricoAtendimento(Long idHistoricoAtendimento) {
        this.idHistoricoAtendimento = idHistoricoAtendimento;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDate dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }
}
