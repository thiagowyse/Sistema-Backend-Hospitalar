package com.projeto.service.prontuarioservice;

import com.projeto.model.Prontuario;
import com.projeto.repository.ProntuarioRepository;

import java.util.List;

public class ProntuarioService implements IProntuarioService{

    private final ProntuarioRepository prontuarioRepository;

    public ProntuarioService(ProntuarioRepository prontuarioRepository) {
        this.prontuarioRepository = prontuarioRepository;
    }

    @Override
    public Prontuario inserirProntuario(Prontuario prontuario) {
        return prontuarioRepository.insert(prontuario);
    }

    @Override
    public Prontuario buscarProntuarioPorId(Long id) {
        return prontuarioRepository.findById(id);
    }

    @Override
    public List<Prontuario> listarTodosProntuarios() {
        return prontuarioRepository.findAll();
    }

    @Override
    public void atualizarProntuario(Prontuario prontuario) {
        prontuarioRepository.update(prontuario);
    }

    @Override
    public void removerProntuario(Long id) {
        prontuarioRepository.delete(id);
    }
}
