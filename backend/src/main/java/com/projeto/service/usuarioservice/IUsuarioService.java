package com.projeto.service.usuarioservice;

import com.projeto.model.Perfil;
import com.projeto.model.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario inserirUsuario(Usuario usuario);

    Usuario buscarUsuarioPorId(Long id);

    List<Usuario> listarTodosUsuarios();

    void atualizarUsuario(Usuario usuario);

    void removerUsuario(Long id);

}
