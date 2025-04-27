package com.projeto.service.agendamentoservice;

import com.projeto.model.Agendamento;

import java.util.List;

public interface IAgendamentoService {

    Agendamento inserirAgendamento(Agendamento agendamento);

    Agendamento buscarAgendamentoPorId(Long id);

    List<Agendamento> listarTodosAgendamentos();

    void atualizarAgendamento(Agendamento agendamento);

    void removerAgendamento(Long id);
}
