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
        } else if (method.equals(HttpMethod.GET.getMethod()) && path.matches(ApiRotas.AGENDAMENTO_FIND_BY_ID.getPath())) {
            findById(exchange);
        } else if (method.equals(HttpMethod.POST.getMethod()) && path.equals(ApiRotas.AGENDAMENTO_SAVE.getPath())) {
            save(exchange);
        } else if (method.equals(HttpMethod.PUT.getMethod()) && path.matches(ApiRotas.AGENDAMENTO_UPDATE.getPath())) {
            update(exchange);
        } else if (method.equals(HttpMethod.DELETE.getMethod()) && path.matches(ApiRotas.AGENDAMENTO_DELETE.getPath())) {
            delete(exchange);
        } else {
            sendResponse(exchange, "Rota não encontrada", 404);
        }
    }

    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        List<Agendamento> listAgendamento = agendamentoService.listarTodosAgendamentos();

        String response = gson.toJson(listAgendamento);

        exchange.getResponseHeaders().set("Content-Type", "application/json");

        sendResponse(exchange,response, 200);
    }

    @Override
    public void findById(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        // Extrai os parâmetros da query (ex: ?id=1)
        String query = exchange.getRequestURI().getQuery();
        Long id = null;

        if (query != null && query.contains("id=")) {
            String[] params = query.split("&");
            for (String param : params) {
                if (param.startsWith("id=")) {
                    id = Long.parseLong(param.split("=")[1]);
                    break;
                }
            }
        }

        if (id == null) {
            sendResponse(exchange, "Parâmetro 'id' é obrigatório", 400);
            return;
        }

        Agendamento agendamento = agendamentoService.buscarAgendamentoPorId(id);
        if (agendamento == null) {
            sendResponse(exchange, "Agendamento não encontrado", 404);
            return;
        }

//        Paciente paciente = pacienteService.buscarPacientePorId(agendamento.getIdPaciente());
//        agendamento.setPaciente(paciente);
//        Medico medico = medicoService.buscarMedicoPorId(agendamento.getIdMedico());
//        agendamento.setMedico(medico);

        String response = gson.toJson(agendamento);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        sendResponse(exchange, response, 200);
    }

    @Override
    public void save(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        try {
            String body = new String(exchange.getRequestBody().readAllBytes());
            System.out.println("[DEBUG] JSON recebido: " + body);

            Agendamento agendamento = gson.fromJson(body, Agendamento.class);

            agendamentoService.inserirAgendamento(agendamento);

            String response = "Agendamento salvo com sucesso!";
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            sendResponse(exchange, response, 201);

        } catch (Exception e) {
            e.printStackTrace(); // Mostra o erro no console
            sendResponse(exchange, "Erro ao processar o agendamento: " + e.getMessage(), 400);
        }
    }


    @Override
    public void update(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        String body = new String(exchange.getRequestBody().readAllBytes());

        Agendamento agendamento = gson.fromJson(body, Agendamento.class);
        agendamento.setIdAgendamento(id);
        agendamentoService.atualizarAgendamento(agendamento);

        sendResponse(exchange, "Agendamento atualizado com sucesso", 200);
    }

    @Override
    public void delete(HttpExchange exchange) throws IOException {
        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        agendamentoService.removerAgendamento(id);
        sendResponse(exchange, "Agendamento deletado com sucesso", 200);
    }

    private Long extractIdFromPath(String path) {
        String[] parts = path.split("/");
        return Long.parseLong(parts[parts.length - 1]);
    }

}



