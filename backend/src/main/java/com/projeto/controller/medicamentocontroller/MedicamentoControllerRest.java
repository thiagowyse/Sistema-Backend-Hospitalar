package com.projeto.controller.medicamentocontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Medicamento;
import com.projeto.repository.MedicamentoRepository;
import com.projeto.server.RootController;
import com.projeto.service.medicamentoservice.MedicamentoService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

public class MedicamentoControllerRest extends RootController implements IMedicamentoControllerRest {

    MedicamentoRepository medicamentoRepository = new MedicamentoRepository();
    MedicamentoService medicamentoService = new MedicamentoService(medicamentoRepository);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.MEDICAMENTO_FIND_ALL.getPath())){
            findAll(exchange);
        }
    }

    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        List<Medicamento> resposta = medicamentoService.listarTodosMedicamentos();

        String response = gson.toJson(resposta);

        exchange.getResponseHeaders().set("Content-Type", "application/json");

        sendResponse(exchange,response, 200);
    }
}
