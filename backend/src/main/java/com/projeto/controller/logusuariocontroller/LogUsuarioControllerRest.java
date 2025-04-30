package com.projeto.controller.logusuariocontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Atestado;
import com.projeto.model.LogUsuario;
import com.projeto.repository.LogUsuarioRepository;
import com.projeto.server.RootController;
import com.projeto.service.logusuarioservice.LogUsuarioService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

public class LogUsuarioControllerRest extends RootController implements ILogUsuarioControllerRest {

    LogUsuarioRepository logUsuarioRepository = new LogUsuarioRepository();
    LogUsuarioService logUsuarioService = new LogUsuarioService(logUsuarioRepository);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.LOG_USUARIO_FIND_ALL.getPath())){
            findAll(exchange);
        }
    }

    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        List<LogUsuario> resposta = logUsuarioService.listarTodosLogsUsuarios();

        String response = gson.toJson(resposta);

        exchange.getResponseHeaders().set("Content-Type", "application/json");

        sendResponse(exchange,response, 200);
    }
}
