package com.projeto.controller.agendamentocontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Agendamento;
import com.projeto.model.Medico;
import com.projeto.model.Paciente;
import com.projeto.repository.AgendamentoRepository;
import com.projeto.repository.MedicoRepository;
import com.projeto.repository.PacienteRepository;
import com.projeto.server.RootController;
import com.projeto.service.agendamentoservice.AgendamentoService;
import com.projeto.service.medicoservice.MedicoService;
import com.projeto.service.pacienteservice.PacienteService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoControllerRest extends RootController implements IAgendamentoControllerRest {

    AgendamentoRepository agendamentoRepository = new AgendamentoRepository();
    AgendamentoService agendamentoService = new AgendamentoService(agendamentoRepository);

    PacienteRepository pacienteRepository = new PacienteRepository();
    PacienteService pacienteService = new PacienteService(pacienteRepository);

    MedicoRepository medicoRepository = new MedicoRepository();
    MedicoService medicoService = new MedicoService(medicoRepository);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.AGENDAMENTO_FIND_ALL.getPath())){
            findAll(exchange);
        }
    }

    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        List<Agendamento> listAgendamento = agendamentoService.listarTodosAgendamentos();
        List<Agendamento> resposta = new ArrayList<>();

        for(Agendamento agendamento : listAgendamento){
            Paciente paciente = pacienteService.buscarPacientePorId(agendamento.getIdPaciente());
            agendamento.setPaciente(paciente);
            Medico medico = medicoService.buscarMedicoPorId(agendamento.getIdPaciente());
            agendamento.setMedico(medico);
            resposta.add(agendamento);
        }

        String response = gson.toJson(resposta);

        exchange.getResponseHeaders().set("Content-Type", "application/json");

        sendResponse(exchange,response, 200);
    }
}
