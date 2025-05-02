package com.projeto.controller.especialidadecontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Especialidade;
import com.projeto.model.Medico;
import com.projeto.repository.EspecialidadeRepository;
import com.projeto.server.RootController;
import com.projeto.service.especialidadeservice.EspecialidadeService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

public class EspecialidadeControllerRest extends RootController implements IEspecialidadeControllerRest {

    EspecialidadeRepository especialidadeRepository = new EspecialidadeRepository();
    EspecialidadeService especialidadeService = new EspecialidadeService(especialidadeRepository);

    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        // Habilitar CORS
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");

        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.ESPECIALIDADE_FIND_ALL.getPath())){
            findAll(exchange);
        } else if (method.equals(HttpMethod.GET.getMethod()) && path.matches(ApiRotas.ESPECIALIDADE_FIND_BY_ID.getPath())) {
            findById(exchange);
        } else if (method.equals(HttpMethod.POST.getMethod()) && path.equals(ApiRotas.ESPECIALIDADE_CREATE.getPath())) {
            save(exchange);
        } else if (method.equals(HttpMethod.PUT.getMethod()) && path.matches(ApiRotas.ESPECIALIDADE_UPDATE.getPath())) {
            update(exchange);
        } else if (method.equals(HttpMethod.DELETE.getMethod()) && path.matches(ApiRotas.ESPECIALIDADE_DELETE.getPath())) {
            delete(exchange);
        } else {
            sendResponse(exchange, "Rota não encontrada", 404);
        }
    }

    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        List<Especialidade> resposta = especialidadeService.listarTodosEspecialidades();

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

        Especialidade especialidade = especialidadeService.buscarEspecialidadePorId(id);
        if (especialidade == null) {
            sendResponse(exchange, "Especialidade não encontrada", 404);
            return;
        }

        String response = gson.toJson(especialidade);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        sendResponse(exchange, response, 200);
    }

    @Override
    public void update(HttpExchange exchange) throws IOException {

        Gson gson = new Gson();
        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        String body = new String(exchange.getRequestBody().readAllBytes());

        Especialidade especialidade = gson.fromJson(body, Especialidade.class);
        especialidade.setIdEspecialidade(id);
        especialidadeService.atualizarEspecialidades(especialidade);

        sendResponse(exchange, "Especialidade atualizada com sucesso", 200);
    }

    @Override
    public void delete(HttpExchange exchange) throws IOException {
        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        especialidadeService.removerEspecialidade(id);
        sendResponse(exchange, "Especialidade deletada com sucesso", 200);

    }

    @Override
    public void save(HttpExchange exchange) throws IOException {

    }

    private Long extractIdFromPath(String path) {
        String[] parts = path.split("/");
        return Long.parseLong(parts[parts.length - 1]);
    }
}
