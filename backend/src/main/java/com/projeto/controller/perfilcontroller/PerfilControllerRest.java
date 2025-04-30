package com.projeto.controller.perfilcontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Medico;
import com.projeto.model.Perfil;
import com.projeto.repository.PerfilRepository;
import com.projeto.server.RootController;
import com.projeto.service.perfilservice.PerfilService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

public class PerfilControllerRest extends RootController implements IPerfilControllerRest {

    PerfilRepository perfilRepository = new PerfilRepository();
    PerfilService perfilService = new PerfilService(perfilRepository);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.PACIENTE_FIND_ALL.getPath())){
            findAll(exchange);
        }
    }


    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        List<Perfil> resposta = perfilService.listarTodosPerfils();

        String response = gson.toJson(resposta);

        exchange.getResponseHeaders().set("Content-Type", "application/json");

        sendResponse(exchange,response, 200);
    }
}
