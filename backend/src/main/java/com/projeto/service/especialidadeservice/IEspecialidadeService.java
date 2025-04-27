package com.projeto.service.especialidadeservice;

import com.projeto.model.Especialidade;

import java.util.List;

public interface IEspecialidadeService {


    Especialidade inserirEspecialidade(Especialidade especialidade);

    Especialidade buscarEspecialidadePorId(Long id);

    List<Especialidade> listarTodosEspecialidades();

    void atualizarEspecialidades(Especialidade especialidade);

    void removerEspecialidade(Long id);
}
