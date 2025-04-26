package com.projeto.model;

import java.time.LocalDateTime;

public class LogUsuario {

    private Long idLogUsuario;
    private Usuario usuario;
    private String acao;
    private LocalDateTime dataHora;

    public LogUsuario(Long idLogUsuario, Usuario usuario, String acao, LocalDateTime dataHora) {
        this.idLogUsuario = idLogUsuario;
        this.usuario = usuario;
        this.acao = acao;
        this.dataHora = dataHora;
    }

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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}