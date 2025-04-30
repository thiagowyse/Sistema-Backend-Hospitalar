package com.projeto.service.logusuarioservice;

import com.projeto.model.LogUsuario;
import com.projeto.repository.LogUsuarioRepository;

import java.util.List;

public class LogUsuarioService implements ILogUsuarioService{

    private final LogUsuarioRepository logUsuarioRepository;

    public LogUsuarioService(LogUsuarioRepository logUsuarioRepository) {
        this.logUsuarioRepository = logUsuarioRepository;
    }

    @Override
    public LogUsuario inserirLogUsuario(LogUsuario logUsuario) {
        return logUsuarioRepository.insert(logUsuario);
    }

    @Override
    public LogUsuario buscarLogUsuarioPorId(Long id) {
        return logUsuarioRepository.findById(id);
    }

    @Override
    public List<LogUsuario> listarTodosLogsUsuarios() {
        return logUsuarioRepository.findAll();
    }

    @Override
    public void atualizarLogUsuario(LogUsuario logUsuario) {
        logUsuarioRepository.update(logUsuario);
    }

    @Override
    public void removerLogUsuario(Long id) {
        logUsuarioRepository.delete(id);
    }
}
