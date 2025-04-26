package com.projeto.model;

import java.time.LocalDateTime;

public class Agendamento {

    private Long idAgendamento;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime dataConsulta;
    private String Status;

    public Agendamento(Long idAgendamento, Paciente paciente, Medico medico, LocalDateTime dataConsulta, String status) {
        this.idAgendamento = idAgendamento;
        this.paciente = paciente;
        this.medico = medico;
        this.dataConsulta = dataConsulta;
        Status = status;
    }

    public Long getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Long idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
