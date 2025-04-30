package com.projeto.model;

import java.sql.Date;

public class Paciente {

   private Long idPaciente;
   private String nome;
   private String endereco;
   private String telefone;
   private Date dataNascimento;

    public Paciente(Long idPaciente, String nome, String endereco, String telefone, Date dataNascimento) {
        this.idPaciente = idPaciente;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }
    public Paciente() {}


    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
