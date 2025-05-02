package com.projeto.controller.enfermeirocontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Atestado;
import com.projeto.model.Enfermeiro;
import com.projeto.model.Medico;
import com.projeto.repository.EnfermeiroRepository;
import com.projeto.server.RootController;
import com.projeto.service.enfermeiroservice.EnfermeiroService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

public class EnfermeiroControllerRest extends RootController implements IEnfermeiroControllerRest {

    EnfermeiroRepository enfermeiroRepository = new EnfermeiroRepository();
    EnfermeiroService enfermeiroService = new EnfermeiroService(enfermeiroRepository);

    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        // Habilitar CORS
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");

        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.ENFERMEIRO_FIND_ALL.getPath())){
            findAll(exchange);
        } else if (method.equals(HttpMethod.GET.getMethod()) && path.matches(ApiRotas.ENFERMEIRO_FIND_BY_ID.getPath())) {
            findById(exchange);
        } else if (method.equals(HttpMethod.POST.getMethod()) && path.equals(ApiRotas.ENFERMEIRO_CREATE.getPath())) {
            save(exchange);
        } else if (method.equals(HttpMethod.PUT.getMethod()) && path.matches(ApiRotas.ENFERMEIRO_UPDATE.getPath())) {
            update(exchange);
        } else if (method.equals(HttpMethod.DELETE.getMethod()) && path.matches(ApiRotas.ENFERMEIRO_DELETE.getPath())) {
            delete(exchange);
        } else {
            sendResponse(exchange, "Rota não encontrada", 404);
        }
    }

    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        List<Enfermeiro> resposta = enfermeiroService.listarTodosEnfermeiros();

        String response = gson.toJson(resposta);

        exchange.getResponseHeaders().set("Content-Type", "application/json");

        sendResponse(exchange,response, 200);
    }

    @Override
    public void findById(HttpExchange exchange) throws IOException {

        Gson gson = new Gson();

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

        Enfermeiro enfermeiro = enfermeiroService.buscarEnfermeiroPorId(id);
        if (enfermeiro == null) {
            sendResponse(exchange, "Enfermeiro não encontrado", 404);
            return;
        }

        String response = gson.toJson(enfermeiro);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        sendResponse(exchange, response, 200);
    }

    @Override
    public void update(HttpExchange exchange) throws IOException {

        Gson gson = new Gson();
        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        String body = new String(exchange.getRequestBody().readAllBytes());

        Enfermeiro enfermeiro = gson.fromJson(body, Enfermeiro.class);
        enfermeiro.setIdEnfermeiro(id);
        enfermeiroService.atualizarEnfermeiro(enfermeiro);

        sendResponse(exchange, "Enfermeiro atualizado com sucesso", 200);
    }

    @Override
    public void delete(HttpExchange exchange) throws IOException {
        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        enfermeiroService.removerEnfermeiro(id);
        sendResponse(exchange, "Enfermeiro deletado com sucesso", 200);

    }

    @Override
    public void save(HttpExchange exchange) throws IOException {

    }

    private Long extractIdFromPath(String path) {
        String[] parts = path.split("/");
        return Long.parseLong(parts[parts.length - 1]);
    }
}
