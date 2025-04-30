package com.projeto.controller.prontuariocontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Prontuario;
import com.projeto.repository.ProntuarioRepository;
import com.projeto.server.RootController;
import com.projeto.service.prontuarioservice.ProntuarioService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

public class ProntuarioControllerRest extends RootController implements IProntuarioControllerRest {

    ProntuarioRepository prontuarioRepository = new ProntuarioRepository();
    ProntuarioService prontuarioService = new ProntuarioService(prontuarioRepository);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.PRONTUARIO_FIND_ALL.getPath())){
            findAll(exchange);
        }
    }

    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        List<Prontuario> resposta = prontuarioService.listarTodosProntuarios();

        String response = gson.toJson(resposta);

        exchange.getResponseHeaders().set("Content-Type", "application/json");

        sendResponse(exchange,response, 200);
    }
}
