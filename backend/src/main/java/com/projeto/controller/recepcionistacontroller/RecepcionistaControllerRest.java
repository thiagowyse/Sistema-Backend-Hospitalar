package com.projeto.controller.recepcionistacontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Recepcionista;
import com.projeto.model.Usuario;
import com.projeto.repository.RecepcionistaRepository;
import com.projeto.server.RootController;
import com.projeto.service.recepcionistaservice.RecepcionistaService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

public class RecepcionistaControllerRest extends RootController implements IRecepcionistaControllerRest {

    RecepcionistaRepository recepcionistaRepository = new RecepcionistaRepository();
    RecepcionistaService recepcionistaService = new RecepcionistaService(recepcionistaRepository);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.RECEPCIONISTA_FIND_ALL.getPath())){
            findAll(exchange);
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
}
