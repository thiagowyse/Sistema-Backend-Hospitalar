package com.projeto.model;

public class Medicamento {

    private Long idMedicamento;
    private String nome;
    private String dosagem;
    private String viaAdministracao;

    public Medicamento(Long idMedicamento, String nome, String dosagem) {
        this.idMedicamento = idMedicamento;
        this.nome = nome;
        this.dosagem = dosagem;
    }
    public Medicamento() {}

    public Long getIdMedicamento() {
        return idMedicamento;
    }
    
    public String getViaAdministracao() {
		return viaAdministracao;
	}

	public void setViaAdministracao(String viaAdministracao) {
		this.viaAdministracao = viaAdministracao;
	}

	public void setIdMedicamento(Long idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }
}
