package com.projeto.service.exameservice;

import com.projeto.model.Exame;

import java.util.List;

public interface IExameService {

    Exame inserirExame(Exame exame);

    Exame buscarExamePorId(Long id);

    List<Exame> listarTodosExames();

    void atualizarExame(Exame exame);

    void removerExame(Long id);
}
