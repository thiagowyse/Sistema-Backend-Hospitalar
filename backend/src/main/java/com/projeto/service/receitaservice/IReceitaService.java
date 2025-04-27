package com.projeto.service.receitaservice;

import com.projeto.model.Perfil;
import com.projeto.model.Receita;

import java.util.List;

public interface IReceitaService {

    Receita inserirReceita(Receita receita);

    Receita buscarReceitaPorId(Long id);

    List<Receita> listarTodosReceitas();

    void atualizarReceita(Receita receita);

    void removerReceita(Long id);
}
