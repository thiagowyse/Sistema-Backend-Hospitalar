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

public class MedicoControllerRest extends RootController implements IMedicoControllerRest {

    MedicoRepository medicoRepository = new MedicoRepository();
    MedicoService medicoService = new MedicoService(medicoRepository);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.MEDICO_FIND_ALL.getPath())){
            findAll(exchange);
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
}
