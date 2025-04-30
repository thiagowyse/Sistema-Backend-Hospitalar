package com.projeto.service.agendamentoservice;

import com.projeto.model.Agendamento;
import com.projeto.repository.AgendamentoRepository;

import java.util.List;

public class AgendamentoService implements IAgendamentoService{

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    @Override
    public Agendamento inserirAgendamento(Agendamento agendamento) {
        return agendamentoRepository.insert(agendamento) ;
    }

    @Override
    public Agendamento buscarAgendamentoPorId(Long id) {
        return agendamentoRepository.findById(id);
    }

    @Override
    public List<Agendamento> listarTodosAgendamentos() {
        return agendamentoRepository.findAll();
    }

    @Override
    public void atualizarAgendamento(Agendamento agendamento) {
        agendamentoRepository.update(agendamento);
    }

    @Override
    public void removerAgendamento(Long id) {
        agendamentoRepository.delete(id);
    }
}
