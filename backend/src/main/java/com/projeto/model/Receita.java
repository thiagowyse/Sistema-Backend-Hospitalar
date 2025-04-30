package com.projeto.model;

import java.util.List;
import java.sql.Date;

public class Receita {

    private Long idReceita;
    private Long prontuarioId;
    private Date dataReceita;
    private Date validade;
    private String descricao;
    private String status;
    private List<Medicamento> medicamentos;

    public Receita(Long idReceita, Long prontuarioId, Date dataReceita, Date validade, String descricao, String status, List<Medicamento> medicamentos) {
        this.idReceita = idReceita;
        this.prontuarioId = prontuarioId;
        this.dataReceita = dataReceita;
        this.validade = validade;
        this.descricao = descricao;
        this.status = status;
        this.medicamentos = medicamentos;
    }
    public Receita() {}

    public Long getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Long idReceita) {
        this.idReceita = idReceita;
    }

    public Long getProntuarioId() {
        return prontuarioId;
    }

    public void setProntuarioId(Long prontuarioId) {
        this.prontuarioId = prontuarioId;
    }

    public Date getDataReceita() {
		return dataReceita;
	}
	public void setDataReceita(Date dataReceita) {
		this.dataReceita = dataReceita;
	}
	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }
}
