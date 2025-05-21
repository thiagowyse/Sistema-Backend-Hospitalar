package com.projeto.controller.recepcionistacontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Perfil;
import com.projeto.model.Recepcionista;
import com.projeto.model.Usuario;
import com.projeto.repository.RecepcionistaRepository;
import com.projeto.server.RootController;
import com.projeto.service.recepcionistaservice.RecepcionistaService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class RecepcionistaControllerRest extends RootController implements IRecepcionistaControllerRest {

    RecepcionistaRepository recepcionistaRepository = new RecepcionistaRepository();
    RecepcionistaService recepcionistaService = new RecepcionistaService(recepcionistaRepository);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.RECEPCIONISTA_FIND_ALL.getPath())){
            findAll(exchange);
        } else if (method.equals(HttpMethod.GET.getMethod()) && path.matches(ApiRotas.RECEPCIONISTA_FIND_BY_ID.getPath())) {
            findById(exchange);
        }
        else if (method.equals(HttpMethod.GET.getMethod()) && path.matches(ApiRotas.RECEPCIONISTA_FIND_ASSINATURA_BY_ID.getPath())) {
            findById(exchange);
        } else if (method.equals(HttpMethod.POST.getMethod()) && path.equals(ApiRotas.RECEPCIONISTA_SAVE.getPath())) {
            save(exchange);
        } else if (method.equals(HttpMethod.PUT.getMethod()) && path.matches(ApiRotas.RECEPCIONISTA_UPDATE.getPath())) {
            update(exchange);
        } else if (method.equals(HttpMethod.DELETE.getMethod()) && path.matches(ApiRotas.RECEPCIONISTA_DELETE.getPath())) {
            delete(exchange);
        } else {
            sendResponse(exchange, "Rota não encontrada", 404);
        }
    }

    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        List<Recepcionista> resposta = recepcionistaService.listarTodosRecepcionistas();

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
            Recepcionista recepcionista = recepcionistaService.buscarRecepcionistaPorId(id);
            String response = gson.toJson(recepcionista);
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            sendResponse(exchange, response, 200);

        } catch (IllegalArgumentException e) {
            sendResponse(exchange, e.getMessage(), 400);
        } catch (
        NoSuchElementException e) {
        sendResponse(exchange, e.getMessage(), 404);
        }
    }

    @Override
    public void update(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        String body = new String(exchange.getRequestBody().readAllBytes());

        Recepcionista recepcionista = gson.fromJson(body, Recepcionista.class);
        recepcionista.setIdRecepcionista(id);
        recepcionistaService.atualizarRecepcionista(recepcionista);

        sendResponse(exchange, "Recepcionista atualizado com sucesso", 200);

    }

    @Override
    public void delete(HttpExchange exchange) throws IOException {
        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        recepcionistaService.removerRecepcionista(id);
        sendResponse(exchange, "Recepcionista deletado com sucesso", 200);
    }

    @Override
    public void save(HttpExchange exchange) throws IOException {

    }

    @Override
    public void findAssinaturaById(HttpExchange exchange) throws IOException {
        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        String response=recepcionistaService.encontrarAssinaturaPorId(id);
        sendResponse(exchange, response, 200);

    }

    private Long extractIdFromPath(String path) {
        String[] parts = path.split("/");
        return Long.parseLong(parts[parts.length - 1]);
    }
}
