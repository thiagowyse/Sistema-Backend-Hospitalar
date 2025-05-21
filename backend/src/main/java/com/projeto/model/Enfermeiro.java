package com.projeto.model;

public class Enfermeiro extends Usuario {

    private Long idEnfermeiro;
    private String coren;
    //private Usuario usuario;

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

    @Override
    public String gerarAssinatura(){
        return "Enf. "+getNome() + " - Enfermeiro (COREN: " + coren + ")";
    }
}

