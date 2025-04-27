package com.projeto.service.historicoatendimentoservice;

import com.projeto.model.HistoricoAtendimento;

import java.util.List;

public interface IHistoricoAtendimentoService {

    HistoricoAtendimento inserirHistoricoAtendimento(HistoricoAtendimento historicoAtendimento);

    HistoricoAtendimento buscarHistoricoAtendimentoPorId(Long id);

    List<HistoricoAtendimento> listarTodosHistoricoAtendimentos();

    void atualizarHistoricoAtendimento(HistoricoAtendimento historicoAtendimento);

    void removerHistoricoAtendimento(Long id);
}
