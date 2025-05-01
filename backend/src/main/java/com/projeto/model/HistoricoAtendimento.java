package com.projeto.model;

import java.sql.Date;

public class HistoricoAtendimento {

    private Long idHistoricoAtendimento;
    private Paciente paciente;
    private Medico medico;
    private String descricao;
    private Date dataAtendimento;

    public HistoricoAtendimento(Long idHistoricoAtendimento, Paciente paciente, Medico medico, String descricao, Date dataAtendimento) {
        this.idHistoricoAtendimento = idHistoricoAtendimento;
        this.paciente = paciente;
        this.medico = medico;
        this.descricao = descricao;
        this.dataAtendimento = dataAtendimento;
    }
    public HistoricoAtendimento() {}

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

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }
}
