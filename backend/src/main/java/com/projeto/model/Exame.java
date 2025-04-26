package com.projeto.model;

public class Exame {

    private Long idExame;
    private String nome;
    private String descricao;

    public Exame(Long idExame, String nome, String descricao) {
        this.idExame = idExame;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getIdExame() {
        return idExame;
    }

    public void setIdExame(Long idExame) {
        this.idExame = idExame;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
