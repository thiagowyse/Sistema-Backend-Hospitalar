package com.projeto.controller.enfermeirocontroller;

import com.google.gson.Gson;
import com.projeto.model.Atestado;
import com.projeto.model.Enfermeiro;
import com.projeto.repository.EnfermeiroRepository;
import com.projeto.server.RootController;
import com.projeto.service.enfermeiroservice.EnfermeiroService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

public class EnfermeiroControllerRest extends RootController implements IEnfermeiroControllerRest {

    EnfermeiroRepository enfermeiroRepository = new EnfermeiroRepository();
    EnfermeiroService enfermeiroService = new EnfermeiroService(enfermeiroRepository);

    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        List<Enfermeiro> resposta = enfermeiroService.listarTodosEnfermeiros();

        String response = gson.toJson(resposta);

        exchange.getResponseHeaders().set("Content-Type", "application/json");

        sendResponse(exchange,response, 200);
    }
}
