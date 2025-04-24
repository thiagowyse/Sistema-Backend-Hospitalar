package com.projeto.model;

public class Prontuario {
    private Paciente paciente;
    private Medico medico;
    private String diagnostico;

    public Prontuario(Paciente paciente, Medico medico, String diagnostico) {
        this.paciente = paciente;
        this.medico = medico;
        this.diagnostico = diagnostico;
    }

    public void exibirDetalhes() {
        System.out.println("Prontuário do paciente: " + paciente.getNome());
        System.out.println("Diagnóstico: " + diagnostico);
        System.out.println("Médico responsável: " + medico.getPerfil());
    }
}
