package com.projeto.controller.prontuarioexamecontroller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.projeto.controller.prontuariocontroller.IProntuarioControllerRest;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Prontuario;
import com.projeto.model.ProntuarioExame;
import com.projeto.repository.ProntuarioExameRepository;
import com.projeto.server.RootController;
import com.projeto.service.prontuarioexameservice.ProntuarioExameService;
import com.projeto.util.LocalDateAdapter;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ProntuarioExameControllerRest extends RootController implements IProntuarioControllerRest {

    ProntuarioExameRepository prontuarioExameRepository = new ProntuarioExameRepository();
    ProntuarioExameService prontuarioExameService = new ProntuarioExameService(prontuarioExameRepository);


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        // Configurar CORS
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if ("OPTIONS".equalsIgnoreCase(method)) {
            exchange.sendResponseHeaders(204, -1); // Sem conteúdo
            return;
        }

        if (method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.PRONTUARIO_EXAME_FIND_ALL.getPath())) {
            findAll(exchange);
        } else if (method.equals(HttpMethod.GET.getMethod()) && path.matches(ApiRotas.PRONTUARIO_EXAME_FIND_BY_ID.getPath())) {
            findById(exchange);
        } else if (method.equals(HttpMethod.POST.getMethod()) && path.equals(ApiRotas.PRONTUARIO_EXAME_SAVE.getPath())) {
            save(exchange);
        } else if (method.equals(HttpMethod.PUT.getMethod()) && path.matches(ApiRotas.PRONTUARIO_EXAME_UPDATE.getPath())) {
            update(exchange);
        } else if (method.equals(HttpMethod.DELETE.getMethod()) && path.matches(ApiRotas.PRONTUARIO_EXAME_DELETE.getPath())) {
            delete(exchange);
        } else {
            sendResponse(exchange, "Rota não encontrada", 404);
        }
    }
    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()) // Registra o adaptador
                .create();

        try {
            List<ProntuarioExame> resposta = prontuarioExameService.listarTodosProntuariosExame();
            String response = gson.toJson(resposta);
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            sendResponse(exchange, response, 200);
        } catch (Exception e) {
            System.err.println("Erro ao processar findAll: " + e.getMessage());
            sendResponse(exchange, "{\"error\":\"Erro interno no servidor\"}", 500);
        }


    }

    @Override
    public void findById(HttpExchange exchange) throws IOException {

    }

    @Override
    public void update(HttpExchange exchange) throws IOException {

    }

    @Override
    public void delete(HttpExchange exchange) throws IOException {

    }

    @Override
    public void save(HttpExchange exchange) throws IOException {
        // Cria o Gson com o adaptador customizado para LocalDate
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()) // Registra o adaptador para LocalDate
                .create();

        String body = new String(exchange.getRequestBody().readAllBytes());

        System.out.println("JSON recebido: " + body);

        try {
            // Converte o corpo JSON para um objeto Prontuario
            ProntuarioExame prontuarioExame = gson.fromJson(body, ProntuarioExame.class);


            System.out.println("Objeto Prontuário: " + prontuarioExame);

            prontuarioExameService.inserirProntuarioExame(prontuarioExame);

            // Resposta para sucesso
            sendResponse(exchange, "Prontuário Exame salvo com sucesso", 201);
        } catch (Exception e) {
            e.printStackTrace(); // Mostra o erro completo no console
            sendResponse(exchange, "Erro ao salvar prontuário exame: " + e.getMessage(), 500);
        }

    }
}
