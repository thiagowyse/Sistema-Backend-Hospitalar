package com.projeto.controller.receitacontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Perfil;
import com.projeto.model.Receita;
import com.projeto.repository.ReceitaRepository;
import com.projeto.server.RootController;
import com.projeto.service.receitaservice.ReceitaService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

public class ReceitaControllerRest extends RootController implements IReceitaControllerRest {

    ReceitaRepository receitaRepository = new ReceitaRepository();
    ReceitaService receitaService = new ReceitaService(receitaRepository);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.RECEITA_FIND_ALL.getPath())){
            findAll(exchange);
        }
    }

    @Override
    public void findAll(HttpExchange exchange) throws IOException {

        Gson gson = new Gson();

        List<Receita> resposta = receitaService.listarTodosReceitas();

        String response = gson.toJson(resposta);

        exchange.getResponseHeaders().set("Content-Type", "application/json");

        sendResponse(exchange,response, 200);
    }
}
