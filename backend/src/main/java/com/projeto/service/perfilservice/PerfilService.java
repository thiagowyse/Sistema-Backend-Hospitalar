package com.projeto.service.perfilservice;

import com.projeto.model.Perfil;
import com.projeto.repository.PerfilRepository;

import java.util.List;

public class PerfilService implements IPerfilService {

    private PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository){
        this.perfilRepository = perfilRepository;
    }


    @Override
    public Perfil inserirPerfil(Perfil perfil) {
        return perfilRepository.insert(perfil);
    }

    @Override
    public Perfil buscarPerfilPorId(Long id) {
        return perfilRepository.findById(id);
    }

    @Override
    public List<Perfil> listarTodosPerfils() {
        return perfilRepository.findAll();
    }

    @Override
    public void atualizarPerfil(Perfil perfil) {
        perfilRepository.update(perfil);
    }

    @Override
    public void removerPerfil(Long id) {
        perfilRepository.delete(id);
    }
}
