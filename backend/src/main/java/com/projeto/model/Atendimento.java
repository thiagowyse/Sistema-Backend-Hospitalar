package com.projeto.model;

public class Atendimento {
    private Paciente paciente;
    private Usuario usuario;
    private String descricao;

    public Atendimento(Paciente paciente, Usuario usuario, String descricao) {
        this.paciente = paciente;
        this.usuario = usuario;
        this.descricao = descricao;
    }


    public void exibirDetalhes() {
        System.out.println("Atendimento de " + paciente.getNome());
        System.out.println("Descrito por: " + usuario.getPerfil());
        System.out.println("Descrição: " + descricao);
    }
}