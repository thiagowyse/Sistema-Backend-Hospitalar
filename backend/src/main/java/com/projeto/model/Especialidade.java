package com.projeto.model;


public class Especialidade {

    private Long idEspecialidade;
    private String nome;


    public Especialidade(String nome, Long idEspecialidade) {
        this.nome = nome;
        this.idEspecialidade = idEspecialidade;
    }
    public Especialidade() {}

    public Long getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Long idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
