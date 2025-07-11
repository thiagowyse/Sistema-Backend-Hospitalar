package com.projeto.controller.declaracaocontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Atestado;
import com.projeto.model.Declaracao;
import com.projeto.model.Medico;
import com.projeto.model.Paciente;
import com.projeto.repository.DeclaracaoRepository;
import com.projeto.repository.MedicoRepository;
import com.projeto.repository.PacienteRepository;
import com.projeto.server.RootController;
import com.projeto.service.declaracaoservice.DeclaracaoService;
import com.projeto.service.medicoservice.MedicoService;
import com.projeto.service.pacienteservice.PacienteService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeclaracaoControllerRest extends RootController implements IDeclaracaoControllerRest {

    DeclaracaoRepository declaracaoRepository = new DeclaracaoRepository();
    DeclaracaoService declaracaoService = new DeclaracaoService(declaracaoRepository);



    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        // Habilitar CORS
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");

        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.DECLARACAO_FIND_ALL.getPath())){
            findAll(exchange);
        } else if (method.equals(HttpMethod.GET.getMethod()) && path.matches(ApiRotas.DECLARACAO_FIND_BY_ID.getPath())) {
            findById(exchange);
        } else if (method.equals(HttpMethod.POST.getMethod()) && path.equals(ApiRotas.DECLARACAO_CREATE.getPath())) {
            save(exchange);
        } else if (method.equals(HttpMethod.PUT.getMethod()) && path.matches(ApiRotas.DECLARACAO_UPDATE.getPath())) {
            update(exchange);
        } else if (method.equals(HttpMethod.DELETE.getMethod()) && path.matches(ApiRotas.DECLARACAO_DELETE.getPath())) {
            delete(exchange);
        } else {
            sendResponse(exchange, "Rota não encontrada", 404);
        }
    }

    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        List<Declaracao> resposta = declaracaoService.listarTodosDeclaracoes();

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

        Declaracao declaracao = declaracaoService.buscarDeclaracaoPorId(id);
        if (declaracao == null) {
            sendResponse(exchange, "Declaracao não encontrado", 404);
            return;
        }

        String response = gson.toJson(declaracao);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        sendResponse(exchange, response, 200);
    }

    @Override
    public void update(HttpExchange exchange) throws IOException {

        Gson gson = new Gson();
        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        String body = new String(exchange.getRequestBody().readAllBytes());

        Declaracao declaracao = gson.fromJson(body, Declaracao.class);
        declaracao.setIdDeclaracao(id);
        declaracaoService.atualizarDeclaracao(declaracao);

        sendResponse(exchange, "Declaracao atualizada com sucesso", 200);
    }

    @Override
    public void delete(HttpExchange exchange) throws IOException {
        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        declaracaoService.removerDeclaracao(id);
        sendResponse(exchange, "Declaracao deletada com sucesso", 200);

    }

    @Override
    public void save(HttpExchange exchange) throws IOException {

    }

    private Long extractIdFromPath(String path) {
        String[] parts = path.split("/");
        return Long.parseLong(parts[parts.length - 1]);
    }
}
