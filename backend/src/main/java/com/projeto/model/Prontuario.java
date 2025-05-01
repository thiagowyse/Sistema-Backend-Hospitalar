package com.projeto.model;

import java.util.List;
import java.sql.Date;

public class Prontuario {

    private Long idProntuario;
    private Paciente paciente;
    private Medico medico;
    private String descricao;
    private Date dataCriacao;
    private List<Receita> receitas;
    private List<Exame> exames;

    public Prontuario(Long idProntuario, Paciente paciente, Medico medico, String descricao, List<Receita> receitas, List<Exame> exames) {
        this.idProntuario = idProntuario;
        this.paciente = paciente;
        this.medico = medico;
        this.descricao = descricao;
        this.receitas = receitas;
        this.exames = exames;
    }
    public Prontuario() {}

    public Long getIdProntuario() {
        return idProntuario;
    }

    public void setIdProntuario(Long idProntuario) {
        this.idProntuario = idProntuario;
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

    public List<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }

    public List<Exame> getExames() {
        return exames;
    }

    public void setExames(List<Exame> exames) {
        this.exames = exames;
    }


	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
