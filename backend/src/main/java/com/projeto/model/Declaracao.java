package com.projeto.model;

import java.sql.Date;

public class Declaracao {

    private Long idDeclaracao;
    private Paciente paciente;
    private String tipoDeclaracao;
    private Medico medico;
    private Date dataValidade;
    private Date dataEmissao;
    private String descricao;

    public Long getIdDeclaracao() {
        return idDeclaracao;
    }

    public void setIdDesclaracao(Long idDeclaracao) {
        this.idDeclaracao = idDeclaracao;
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


	public String getTipoDeclaracao() {
		return tipoDeclaracao;
	}

	public void setTipoDeclaracao(String tipoDeclaracao) {
		this.tipoDeclaracao = tipoDeclaracao;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public void setIdDeclaracao(Long idDeclaracao) {
		this.idDeclaracao = idDeclaracao;
	}

	public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
