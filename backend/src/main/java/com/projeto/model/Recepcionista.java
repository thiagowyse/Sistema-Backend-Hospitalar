package com.projeto.model;

public class Recepcionista extends Usuario {

    private Long idRecepcionista;

    public Recepcionista(Long idUsuario, String nome, String login, String email, String senha, Perfil perfil, Long idRecepcionista) {
        super(idUsuario, nome, login, email, senha, perfil);
        this.idRecepcionista = idRecepcionista;
    }

    public Long getIdRecepcionista() {
        return idRecepcionista;
    }

    public void setIdRecepcionista(Long idRecepcionista) {
        this.idRecepcionista = idRecepcionista;
    }
}
