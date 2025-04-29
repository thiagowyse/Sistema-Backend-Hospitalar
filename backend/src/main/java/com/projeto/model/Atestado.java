package com.projeto.model;

import java.sql.Date;

public class Atestado {

    private Long idAtestado;
    private Long idPaciente;
    private Paciente paciente;
    private Long idMedico;
    private Medico medico;
    private Date dataEmissao;
    private Date dataValidade;
    private String descricao;

    public Atestado(Long idAtestado, Paciente paciente, Medico medico, Date dataEmissao, Date dataValidade, String descricao) {
        this.idAtestado = idAtestado;
        this.paciente = paciente;
        this.medico = medico;
        this.dataEmissao = dataEmissao;
        this.dataValidade = dataValidade;
        this.descricao = descricao;
    }
    public Atestado() {}

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

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
	public Long getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}
	public Long getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(Long idMedico) {
		this.idMedico = idMedico;
	}
    
}
