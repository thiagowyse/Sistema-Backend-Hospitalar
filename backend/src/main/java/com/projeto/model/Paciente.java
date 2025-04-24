package com.projeto.model;

public class Paciente {

    private String nome;
    private int idPaciente;

    public Paciente(String nome, int idPaciente) {
        this.nome = nome;
        this.idPaciente = idPaciente;
    }

    public String getNome() {
        return nome;
    }

    public int getIdPaciente() {
        return idPaciente;
    }
}
