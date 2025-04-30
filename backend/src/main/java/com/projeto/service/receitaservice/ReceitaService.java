package com.projeto.service.receitaservice;

import com.projeto.model.Receita;
import com.projeto.repository.ReceitaRepository;

import java.util.List;

public class ReceitaService implements IReceitaService{

    private final ReceitaRepository receitaRepository;

    public ReceitaService(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    @Override
    public Receita inserirReceita(Receita receita) {
        return receitaRepository.insert(receita);
    }

    @Override
    public Receita buscarReceitaPorId(Long id) {
        return receitaRepository.findById(id);
    }

    @Override
    public List<Receita> listarTodosReceitas() {
        return receitaRepository.findAll();
    }

    @Override
    public void atualizarReceita(Receita receita) {
        receitaRepository.update(receita);
    }

    @Override
    public void removerReceita(Long id) {
        receitaRepository.delete(id);
    }
}
