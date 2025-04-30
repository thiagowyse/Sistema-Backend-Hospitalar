package com.projeto.service.historicoatendimentoservice;

import com.projeto.model.HistoricoAtendimento;
import com.projeto.repository.HistoricoAtendimentoRepository;

import java.util.List;

public class HistoricoAtendimentoService implements IHistoricoAtendimentoService{

    private final HistoricoAtendimentoRepository historicoAtendimentoRepository;

    public HistoricoAtendimentoService(HistoricoAtendimentoRepository historicoAtendimentoRepository) {
        this.historicoAtendimentoRepository = historicoAtendimentoRepository;
    }

    @Override
    public HistoricoAtendimento inserirHistoricoAtendimento(HistoricoAtendimento historicoAtendimento) {
        return historicoAtendimentoRepository.insert(historicoAtendimento);
    }

    @Override
    public HistoricoAtendimento buscarHistoricoAtendimentoPorId(Long id) {
        return historicoAtendimentoRepository.findById(id);
    }

    @Override
    public List<HistoricoAtendimento> listarTodosHistoricoAtendimentos() {
        return historicoAtendimentoRepository.findAll();
    }

    @Override
    public void atualizarHistoricoAtendimento(HistoricoAtendimento historicoAtendimento) {
        historicoAtendimentoRepository.update(historicoAtendimento);
    }

    @Override
    public void removerHistoricoAtendimento(Long id) {
        historicoAtendimentoRepository.delete(id);
    }
}
