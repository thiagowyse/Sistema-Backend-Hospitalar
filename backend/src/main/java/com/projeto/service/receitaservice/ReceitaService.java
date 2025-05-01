package com.projeto.service.receitaservice;

import com.projeto.model.Prontuario;
import com.projeto.model.Receita;
import com.projeto.repository.ProntuarioRepository;
import com.projeto.repository.ReceitaRepository;
import com.projeto.service.prontuarioservice.ProntuarioService;

import java.util.ArrayList;
import java.util.List;

public class ReceitaService implements IReceitaService{

    private final ReceitaRepository receitaRepository;

    public ReceitaService(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    private final ProntuarioRepository prontuarioRepository = new ProntuarioRepository();
    private final ProntuarioService prontuarioService = new ProntuarioService(prontuarioRepository);


    @Override
    public Receita inserirReceita(Receita receita) {
        return receitaRepository.insert(receita);
    }

    @Override
    public Receita buscarReceitaPorId(Long id) {
        Receita receita = receitaRepository.findById(id);
        Prontuario prontuario = prontuarioService.buscarProntuarioPorId(receita.getProntuario().getIdProntuario());
        receita.setProntuario(prontuario);
        return receita;
    }

    @Override
    public List<Receita> listarTodosReceitas() {
        List<Receita> receitas = receitaRepository.findAll();
        List<Receita> resposta = new ArrayList<>();
        for(Receita receita : receitas){
            Prontuario prontuario = prontuarioService.buscarProntuarioPorId(receita.getProntuario().getIdProntuario());
            receita.setProntuario(prontuario);
            resposta.add(receita);
        }
        return resposta;
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
