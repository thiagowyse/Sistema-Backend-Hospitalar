package com.projeto.controller.especialidadecontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Especialidade;
import com.projeto.repository.EspecialidadeRepository;
import com.projeto.server.RootController;
import com.projeto.service.especialidadeservice.EspecialidadeService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

public class EspecialidadeControllerRest extends RootController implements IEspecialidadeControllerRest {

    EspecialidadeRepository especialidadeRepository = new EspecialidadeRepository();
    EspecialidadeService especialidadeService = new EspecialidadeService(especialidadeRepository);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.ESPECIALIDADE_FIND_ALL.getPath())){
            findAll(exchange);
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
}
