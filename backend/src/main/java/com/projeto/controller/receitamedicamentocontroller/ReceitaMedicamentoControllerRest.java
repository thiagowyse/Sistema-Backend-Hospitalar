package com.projeto.controller.receitamedicamentocontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.ReceitaMedicamento;
import com.projeto.repository.ReceitaMedicamentoRepository;
import com.projeto.server.RootController;
import com.projeto.service.receitamedicamentoservice.ReceitaMedicamentoService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

public class ReceitaMedicamentoControllerRest extends RootController implements IReceitaMedicamentoControllerRest {


    ReceitaMedicamentoRepository receitaMedicamentoRepository = new ReceitaMedicamentoRepository();
    ReceitaMedicamentoService receitaMedicamentoService = new ReceitaMedicamentoService(receitaMedicamentoRepository);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        // Habilitar CORS
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");


        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.RECEITA_MEDICAMENTO_FIND_ALL.getPath())){
            findAll(exchange);
        } else if (method.equals(HttpMethod.GET.getMethod()) && path.matches(ApiRotas.PERFIL_FIND_BY_ID.getPath())) {
            findById(exchange);
        } else if (method.equals(HttpMethod.POST.getMethod()) && path.equals(ApiRotas.PERFIL_SAVE.getPath())) {
            save(exchange);
        } else if (method.equals(HttpMethod.PUT.getMethod()) && path.matches(ApiRotas.PERFIL_UPDATE.getPath())) {
            update(exchange);
        } else if (method.equals(HttpMethod.DELETE.getMethod()) && path.matches(ApiRotas.PERFIL_DELETE.getPath())) {
            delete(exchange);
        } else {
            sendResponse(exchange, "Rota não encontrada", 404);
        }

    }


    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        List<ReceitaMedicamento> resposta = receitaMedicamentoService.listarTodasReceitasMedicamentos();

        String response = gson.toJson(resposta);

        exchange.getResponseHeaders().set("Content-Type", "application/json");

        sendResponse(exchange,response, 200);
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

    }
}
