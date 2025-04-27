package com.projeto.service.perfilservice;

import com.projeto.model.Perfil;

import java.util.List;

public interface IPerfilService {

    Perfil inserirPerfil(Perfil perfil);

    Perfil buscarPerfilPorId(Long id);

    List<Perfil> listarTodosPerfils();

    void atualizarPerfil(Perfil perfil);

    void removerPerfil(Long id);
}
