package com.projeto.controller.historicoatendimentocontroller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IHistoricoAtendimentoControllerRest {

    void findAll(HttpExchange exchange) throws IOException;
}
