package com.projeto.service.prontuarioexameservice;

import com.projeto.model.ProntuarioExame;

import java.util.List;

public interface IProntuarioExameService {

    ProntuarioExame inserirProntuarioExame(ProntuarioExame prontuarioExame);

    ProntuarioExame buscarProntuarioExamePorIdProntuarioAndIdExame(Long idProntuario, Long idExame);

    List<ProntuarioExame> listarTodosProntuariosExame();

    void removerProntuarioExame(Long idProntuario, Long idExame);
}
