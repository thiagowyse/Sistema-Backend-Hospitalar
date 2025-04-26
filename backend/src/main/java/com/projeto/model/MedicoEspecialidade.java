package com.projeto.model;

public class MedicoEspecialidade {

    private Medico medico;
    private Especialidade especialidade;

    public MedicoEspecialidade(Medico medico, Especialidade especialidade) {
        this.medico = medico;
        this.especialidade = especialidade;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
}
