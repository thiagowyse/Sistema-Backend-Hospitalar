package com.projeto.controller.pacientecontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Agendamento;
import com.projeto.model.Medico;
import com.projeto.model.Paciente;
import com.projeto.repository.PacienteRepository;
import com.projeto.server.RootController;
import com.projeto.service.pacienteservice.PacienteService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

public class PacienteControllerRest extends RootController implements IPacienteControllerRest {

    PacienteRepository pacienteRepository = new PacienteRepository();
    PacienteService pacienteService = new PacienteService(pacienteRepository);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        // Habilitar CORS
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");

        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.PACIENTE_FIND_ALL.getPath())){
            findAll(exchange);
        } else if (method.equals(HttpMethod.GET.getMethod()) && path.matches(ApiRotas.PACIENTE_FIND_BY_ID.getPath())) {
            findById(exchange);
        } else if (method.equals(HttpMethod.POST.getMethod()) && path.equals(ApiRotas.PACIENTE_SAVE.getPath())) {
            save(exchange);
        } else if (method.equals(HttpMethod.PUT.getMethod()) && path.matches(ApiRotas.PACIENTE_UPDATE.getPath())) {
            update(exchange);
        } else if (method.equals(HttpMethod.DELETE.getMethod()) && path.matches(ApiRotas.PACIENTE_DELETE.getPath())) {
            delete(exchange);
        } else {
            sendResponse(exchange, "Rota não encontrada", 404);
        }

    }

    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        List<Paciente> resposta = pacienteService.listarTodosPacientes();

        String response = gson.toJson(resposta);

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

        Paciente paciente = pacienteService.buscarPacientePorId(id);
        if (paciente == null) {
            sendResponse(exchange, "Paciente não encontrado", 404);
            return;
        }

        String response = gson.toJson(paciente);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        sendResponse(exchange, response, 200);
    }

    @Override
    public void update(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        String body = new String(exchange.getRequestBody().readAllBytes());

        Paciente paciente = gson.fromJson(body, Paciente.class);
        paciente.setIdPaciente(id);
        pacienteService.atualizarPaciente(paciente);

        sendResponse(exchange, "Paciente atualizado com sucesso", 200);
    }

    @Override
    public void delete(HttpExchange exchange) throws IOException {
        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        pacienteService.removerPaciente(id);
        sendResponse(exchange, "Paciente deletado com sucesso", 200);
    }

    @Override
    public void save(HttpExchange exchange) throws IOException {

    }

    private Long extractIdFromPath(String path) {
        String[] parts = path.split("/");
        return Long.parseLong(parts[parts.length - 1]);
    }
}
