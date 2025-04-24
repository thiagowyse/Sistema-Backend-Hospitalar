package com.projeto.model;

public abstract class Usuario {

    private String nome;
    private String login;
    private String senha;
    private String perfil;

    public Usuario(String nome, String login, String senha, String perfil) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public String getPerfil() {
        return perfil;
    }

    public abstract void acessarSistema();
}
