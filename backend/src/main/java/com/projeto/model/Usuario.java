package com.projeto.model;

public abstract class Usuario {

    private Long idUsuario;
    private String nome;
    private String login;
    private String senha;
    private Perfil perfil;

    public Usuario(Long idUsuario, String nome, String login, String senha, Perfil perfil) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setId(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
