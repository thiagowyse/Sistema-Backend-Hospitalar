package com.projeto.model;

public class Recepcionista extends Usuario{

    private Long idRecepcionista;

    //private Usuario usuario;

    public Long getIdRecepcionista() {
        return idRecepcionista;
    }

    public void setIdRecepcionista(Long idRecepcionista) {
        this.idRecepcionista = idRecepcionista;
    }

    /*public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }*/
    @Override
    public String gerarAssinatura(){
        return getNome() + " - Recepcionista (Matrícula: " + idRecepcionista + ")";
    }
}
