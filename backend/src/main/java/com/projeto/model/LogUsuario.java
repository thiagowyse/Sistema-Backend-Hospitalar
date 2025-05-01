package com.projeto.model;

import java.sql.Date;

public class LogUsuario {

    private Long idLogUsuario;
    private Usuario usuario;
    private String acao;
    private Date dataHora;

    public LogUsuario(Long idLogUsuario, Usuario usuario, String acao, Date dataHora) {
        this.idLogUsuario = idLogUsuario;
        this.usuario = usuario;
        this.acao = acao;
        this.dataHora = dataHora;
    }
    public LogUsuario() {}

    public Long getIdLogUsuario() {
        return idLogUsuario;
    }

    public void setIdLogUsuario(Long idLogUsuario) {
        this.idLogUsuario = idLogUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getAcao() {
        return acao;
    }

	public void setAcao(String acao) {
        this.acao = acao;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }
}