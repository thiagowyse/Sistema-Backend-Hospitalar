package com.projeto.service.logusuarioservice;

import com.projeto.model.LogUsuario;
import com.projeto.model.Usuario;
import com.projeto.repository.LogUsuarioRepository;
import com.projeto.repository.UsuarioRepository;
import com.projeto.service.usuarioservice.UsuarioService;

import java.util.ArrayList;
import java.util.List;

public class LogUsuarioService implements ILogUsuarioService{

    private final LogUsuarioRepository logUsuarioRepository;

    public LogUsuarioService(LogUsuarioRepository logUsuarioRepository) {
        this.logUsuarioRepository = logUsuarioRepository;
    }

    UsuarioRepository usuarioRepository = new UsuarioRepository();
    UsuarioService usuarioService = new UsuarioService(usuarioRepository);

    @Override
    public LogUsuario inserirLogUsuario(LogUsuario logUsuario) {
        return logUsuarioRepository.insert(logUsuario);
    }

    @Override
    public LogUsuario buscarLogUsuarioPorId(Long id) {
        LogUsuario logUsuario = logUsuarioRepository.findById(id);
        Usuario usuario = usuarioService.buscarUsuarioPorId(logUsuario.getUsuario().getIdUsuario());
        logUsuario.setUsuario(usuario);
        return  logUsuario;
    }

    @Override
    public List<LogUsuario> listarTodosLogsUsuarios() {
        List<LogUsuario> logUsuarios = logUsuarioRepository.findAll();
        List<LogUsuario> resposta = new ArrayList<>();
        for(LogUsuario logUsuario : logUsuarios){
            Usuario usuario = usuarioService.buscarUsuarioPorId(logUsuario.getUsuario().getIdUsuario());
            logUsuario.setUsuario(usuario);
            resposta.add(logUsuario);
        }
        return resposta;
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
