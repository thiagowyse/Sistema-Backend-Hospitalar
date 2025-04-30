package com.projeto.controller.historicoatendimentocontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Atestado;
import com.projeto.model.HistoricoAtendimento;
import com.projeto.repository.HistoricoAtendimentoRepository;
import com.projeto.server.RootController;
import com.projeto.service.historicoatendimentoservice.HistoricoAtendimentoService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

public class HistoricoAtendimentoControllerRest extends RootController implements IHistoricoAtendimentoControllerRest {

    HistoricoAtendimentoRepository historicoAtendimentoRepository = new HistoricoAtendimentoRepository();
    HistoricoAtendimentoService historicoAtendimentoService = new HistoricoAtendimentoService(historicoAtendimentoRepository);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.HISTORICO_ATENDIMENTO_FIND_ALL.getPath())){
            findAll(exchange);
        }
    }

    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        List<HistoricoAtendimento> resposta = historicoAtendimentoService.listarTodosHistoricoAtendimentos();

        String response = gson.toJson(resposta);

        exchange.getResponseHeaders().set("Content-Type", "application/json");

        sendResponse(exchange,response, 200);
    }
}
