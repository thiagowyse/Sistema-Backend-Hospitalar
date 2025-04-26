package com.projeto.model;


public class Especialidade {

    private int idEspecialidade;
    private String nome;


    public Especialidade(String nome, int idEspecialidade) {
        this.nome = nome;
        this.idEspecialidade = idEspecialidade;
    }

    public int getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(int idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
