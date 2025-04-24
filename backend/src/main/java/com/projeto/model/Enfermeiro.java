package com.projeto.model;

public class Enfermeiro extends Usuario {

    public Enfermeiro(String nome, String login, String senha) {
        super(nome, login, senha, "enfermeiro");
    }

    @Override
    public void acessarSistema() {
        System.out.println("Enfermeiro acessando o sistema para registrar atendimentos.");
    }

    public void registrarAtendimento(Paciente paciente, String descricao) {
        Atendimento atendimento = new Atendimento(paciente, this, descricao);
        System.out.println("Atendimento registrado para o paciente: " + paciente.getNome());
    }
}

