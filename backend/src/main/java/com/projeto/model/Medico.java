package com.projeto.model;

public class Medico extends Usuario{

    public Medico(String nome, String login, String senha, String perfil) {
        super(nome, login, senha, perfil);
    }

    @Override
    public void acessarSistema() {
        System.out.println("Médico acessando o sistema para gerenciar prontuários.");
    }

    public void criarProntuario(Paciente paciente, String diagnostico) {
        Prontuario prontuario = new Prontuario(paciente, this, diagnostico);
        System.out.println("Prontuário criado para o paciente: " + paciente.getNome());
    }

    public void visualizarProntuario(Prontuario prontuario) {
        prontuario.exibirDetalhes();
    }
}
