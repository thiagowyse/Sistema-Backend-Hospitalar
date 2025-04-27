package com.projeto.service.usuarioservice;

import com.projeto.model.Perfil;
import com.projeto.model.Usuario;
import com.projeto.repository.UsuarioRepository;


import java.util.List;

public class UsuarioService implements IUsuarioService{

    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario inserirUsuario(Usuario usuario) {
        return usuarioRepository.insert(usuario);
    }

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public void atualizarUsuario(Usuario usuario) {
        usuarioRepository.update(usuario);
    }

    @Override
    public void removerUsuario(Long id) {
        usuarioRepository.delete(id);
    }
}
