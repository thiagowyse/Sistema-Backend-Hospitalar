package com.projeto.model;

public class Enfermeiro extends Usuario {

    private Long idEnfermeiro;
    private String coren;

    public Enfermeiro(Long idUsuario, String nome, String login, String senha, Perfil perfil, Long idEnfermeiro, String coren) {
        super(idUsuario, nome, login, senha, perfil);
        this.idEnfermeiro = idEnfermeiro;
        this.coren = coren;
    }

    public Long getIdEnfermeiro() {
        return idEnfermeiro;
    }

    public void setIdEnfermeiro(Long idEnfermeiro) {
        this.idEnfermeiro = idEnfermeiro;
    }

    public String getCoren() {
        return coren;
    }

    public void setCoren(String coren) {
        this.coren = coren;
    }
}

