package com.projeto.service.atestadoservice;

import com.projeto.model.Atestado;
import com.projeto.repository.AtestadoRepository;

import java.util.List;

public class AtestadoService implements IAtestadoService {

    private AtestadoRepository atestadoRepository;

    @Override
    public Atestado inserirAtestado(Atestado atestado) {
        return atestadoRepository.insert(atestado);
    }

    @Override
    public Atestado buscarAtestadoPorId(Long id) {
        return atestadoRepository.findById(id);
    }

    @Override
    public List<Atestado> listarTodosAtestados() {
        return atestadoRepository.findAll();
    }

    @Override
    public void atualizarAtestado(Atestado atestado) {
        atestadoRepository.update(atestado);
    }

    @Override
    public void removerAtestado(Long id) {
        atestadoRepository.delete(id);
    }
}
