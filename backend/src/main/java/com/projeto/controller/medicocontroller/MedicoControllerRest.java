package com.projeto.controller.medicocontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Medico;
import com.projeto.repository.MedicoRepository;
import com.projeto.server.RootController;
import com.projeto.service.medicoservice.MedicoService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class MedicoControllerRest extends RootController implements IMedicoControllerRest {

    MedicoRepository medicoRepository = new MedicoRepository();
    MedicoService medicoService = new MedicoService(medicoRepository);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        // Habilitar CORS
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");

        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.MEDICO_FIND_ALL.getPath())){
            findAll(exchange);
        } else if (method.equals(HttpMethod.GET.getMethod()) && path.matches(ApiRotas.MEDICO_FIND_BY_ID.getPath())) {
            findById(exchange);
        } else if (method.equals(HttpMethod.GET.getMethod()) && path.matches(ApiRotas.MEDICO_FIND_ASSINATURA_BY_ID.getPath())) {
            findAssinaturaById(exchange);
        } else if (method.equals(HttpMethod.POST.getMethod()) && path.equals(ApiRotas.MEDICO_SAVE.getPath())) {
            save(exchange);
        } else if (method.equals(HttpMethod.PUT.getMethod()) && path.matches(ApiRotas.MEDICO_UPDATE.getPath())) {
            update(exchange);
        } else if (method.equals(HttpMethod.DELETE.getMethod()) && path.matches(ApiRotas.MEDICO_DELETE.getPath())) {
            delete(exchange);
        } else {
            sendResponse(exchange, "Rota não encontrada", 404);
        }
    }

    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        List<Medico> resposta = medicoService.listarTodosMedicos();

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
            Medico medico = medicoService.buscarMedicoPorId(id);
            String response = gson.toJson(medico);
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

        Medico medico = gson.fromJson(body, Medico.class);
        medico.setIdMedico(id);
        medicoService.atualizarMedico(medico);

        sendResponse(exchange, "Médico atualizado com sucesso", 200);
    }

    @Override
    public void delete(HttpExchange exchange) throws IOException {
        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        medicoService.removerMedico(id);
        sendResponse(exchange, "Médico deletado com sucesso", 200);

    }

    @Override
    public void save(HttpExchange exchange) throws IOException {

    }

    @Override
    public void findAssinaturaById(HttpExchange exchange) throws IOException {
        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        String response=medicoService.encontrarAssinaturaPorId(id);
        sendResponse(exchange, response, 200);

    }

    private Long extractIdFromPath(String path) {
        String[] parts = path.split("/");
        return Long.parseLong(parts[parts.length - 1]);
    }
}
