package com.projeto.model;

import java.util.List;

public class Medico extends Usuario {

    private Long idMedico;
    private String crm;
    private List<Especialidade> especialidades;

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    @Override
    public String gerarAssinatura(){
        return "Dr. "+getNome() + " - Médico (CRM: " + crm + ")";
    }
}
