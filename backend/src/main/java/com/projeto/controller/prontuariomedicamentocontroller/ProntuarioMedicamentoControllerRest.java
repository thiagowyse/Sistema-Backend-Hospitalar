package com.projeto.controller.prontuariomedicamentocontroller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Prontuario;
import com.projeto.model.ProntuarioMedicamento;
import com.projeto.repository.ProntuarioMedicamentoRepository;
import com.projeto.server.RootController;
import com.projeto.service.prontuariomedicamentoservice.ProntuarioMedicamentoService;
import com.projeto.util.LocalDateAdapter;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ProntuarioMedicamentoControllerRest extends RootController implements IProntuarioMedicamentoControllerRest  {

    ProntuarioMedicamentoRepository prontuarioMedicamentoRepository = new ProntuarioMedicamentoRepository();
    ProntuarioMedicamentoService prontuarioMedicamentoService = new ProntuarioMedicamentoService(prontuarioMedicamentoRepository);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        // Configurar CORS
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if ("OPTIONS".equalsIgnoreCase(method)) {
            exchange.sendResponseHeaders(204, -1); // Sem conteúdo
            return;
        }

        if (method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.PRONTUARIO_MEDICAMENTO_FIND_ALL.getPath())) {
            findAll(exchange);
        } else if (method.equals(HttpMethod.GET.getMethod()) && path.matches(ApiRotas.PRONTUARIO_MEDICAMENTO_FIND_BY_ID.getPath())) {
            findById(exchange);
        } else if (method.equals(HttpMethod.POST.getMethod()) && path.equals(ApiRotas.PRONTUARIO_MEDICAMENTO_SAVE.getPath())) {
            save(exchange);
        } else if (method.equals(HttpMethod.PUT.getMethod()) && path.matches(ApiRotas.PRONTUARIO_MEDICAMENTO_UPDATE.getPath())) {
            update(exchange);
        } else if (method.equals(HttpMethod.DELETE.getMethod()) && path.matches(ApiRotas.PRONTUARIO_MEDICAMENTO_DELETE.getPath())) {
            delete(exchange);
        } else {
            sendResponse(exchange, "Rota não encontrada", 404);
        }
    }


    @Override
    public void findAll(HttpExchange exchange) throws IOException {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        List<ProntuarioMedicamento> resposta = prontuarioMedicamentoService.listarTodosProntuariosExame();

        String response = gson.toJson(resposta);


        exchange.getResponseHeaders().set("Content-Type", "application/json");


        sendResponse(exchange, response, 200);
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
