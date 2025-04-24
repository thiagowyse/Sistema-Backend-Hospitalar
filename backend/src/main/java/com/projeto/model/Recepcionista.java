package com.projeto.model;

public class Recepcionista extends Usuario {

    public Recepcionista(String nome, String login, String senha) {
        super(nome, login, senha, "recepcionista");
    }

    @Override
    public void acessarSistema() {
        System.out.println("Recepcionista acessando o sistema para cadastrar pacientes.");
    }

    public void cadastrarPaciente(Paciente paciente) {
        System.out.println("Paciente cadastrado: " + paciente.getNome());
    }
}
