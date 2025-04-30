package com.projeto.service.exameservice;

import com.projeto.model.Exame;
import com.projeto.repository.ExameRepository;

import java.util.List;

public class ExameService implements IExameService{

    private final ExameRepository exameRepository;

    public ExameService(ExameRepository exameRepository) {
        this.exameRepository = exameRepository;
    }

    @Override
    public Exame inserirExame(Exame exame) {
        return exameRepository.insert(exame);
    }

    @Override
    public Exame buscarExamePorId(Long id) {
        return exameRepository.findById(id);
    }

    @Override
    public List<Exame> listarTodosExames() {
        return exameRepository.findAll();
    }

    @Override
    public void atualizarExame(Exame exame) {
        exameRepository.update(exame);
    }

    @Override
    public void removerExame(Long id) {
        exameRepository.delete(id);
    }
}
