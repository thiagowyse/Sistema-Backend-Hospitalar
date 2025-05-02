package com.projeto.service.usuarioservice;

import com.projeto.model.Perfil;
import com.projeto.model.Usuario;
import com.projeto.repository.PerfilRepository;
import com.projeto.repository.UsuarioRepository;
import com.projeto.service.perfilservice.PerfilService;


import java.util.ArrayList;
import java.util.List;

public class UsuarioService implements IUsuarioService{

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    private final PerfilRepository perfilRepository = new PerfilRepository();
    private final PerfilService perfilService = new PerfilService(perfilRepository);

    @Override
    public Usuario inserirUsuario(Usuario usuario) {
        return usuarioRepository.insert(usuario);
    }

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id);
        Perfil perfil = perfilService.buscarPerfilPorId(usuario.getPerfil().getId());
        usuario.setPerfil(perfil);
        return usuario;
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Usuario> resposta = new ArrayList<>();
        for(Usuario usuario : usuarios){
            Perfil perfil = perfilService.buscarPerfilPorId(usuario.getPerfil().getId());
            usuario.setPerfil(perfil);
            resposta.add(usuario);
        }
        return resposta;
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
