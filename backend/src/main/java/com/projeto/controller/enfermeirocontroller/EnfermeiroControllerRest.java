package com.projeto.controller.enfermeirocontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Atestado;
import com.projeto.model.Enfermeiro;
import com.projeto.model.Medico;
import com.projeto.model.Usuario;
import com.projeto.repository.EnfermeiroRepository;
import com.projeto.server.RootController;
import com.projeto.service.enfermeiroservice.EnfermeiroService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

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
        } else if (method.equals(HttpMethod.GET.getMethod()) && path.matches(ApiRotas.ENFERMEIRO_FIND_ASSINATURA_BY_ID.getPath())) {
            findAssinaturaById(exchange);
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

        try {
            Enfermeiro enfermeiro = enfermeiroService.buscarEnfermeiroPorId(id);
            String response = gson.toJson(enfermeiro);
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            sendResponse(exchange, response, 200);
        } catch (IllegalArgumentException e) {
            sendResponse(exchange, e.getMessage(), 400);
        } catch (NoSuchElementException e) {
            sendResponse(exchange, e.getMessage(), 404);
        }
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
        Gson gson = new Gson();
        String body = new String(exchange.getRequestBody().readAllBytes());

        System.out.println("JSON recebido: " + body);

        try {
            Enfermeiro enfermeiro = gson.fromJson(body, Enfermeiro.class);
            System.out.println("Objeto enfermeio: " + enfermeiro);

            enfermeiroService.inserirEnfermeiro(enfermeiro);
            sendResponse(exchange, "Enfermeiro salvo com sucesso", 201);
        } catch (Exception e) {
            e.printStackTrace(); // Mostra o erro completo no console
            sendResponse(exchange, "Erro ao salvar enfermeiro: " + e.getMessage(), 500);
        }

    }

    @Override
    public void findAssinaturaById(HttpExchange exchange) throws IOException {
        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        String response=enfermeiroService.encontrarAssinaturaPorId(id);
        sendResponse(exchange, response, 200);
    }

    private Long extractIdFromPath(String path) {
        String[] parts = path.split("/");
        return Long.parseLong(parts[parts.length - 1]);
    }
}
