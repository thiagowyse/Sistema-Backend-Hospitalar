package com.projeto.model;

import java.util.List;

public class Medico extends Usuario{

    private Long idMedico;
    private String crm;
    private List<Especialidade> especialidades;

    public Medico(Long idUsuario, String nome, String login, String email, String senha, Perfil perfil, Long idMedico) {
        super(idUsuario, nome, login, email, senha, perfil);
        this.idMedico = idMedico;
    }

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
}
