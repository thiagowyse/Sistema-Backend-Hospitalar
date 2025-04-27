package com.projeto.service.logusuarioservice;

import com.projeto.model.LogUsuario;

import java.util.List;

public interface ILogUsuarioService {

    LogUsuario inserirLogUsuario(LogUsuario logUsuario);

    LogUsuario buscarLogUsuarioPorId(Long id);

    List<LogUsuario> listarTodosLogsUsuarios();

    void atualizarLogUsuario(LogUsuario logUsuario);

    void removerLogUsuario(Long id);
}
