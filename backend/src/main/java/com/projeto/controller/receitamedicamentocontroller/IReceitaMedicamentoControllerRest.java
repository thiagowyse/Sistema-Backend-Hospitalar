package com.projeto.controller.receitamedicamentocontroller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IReceitaMedicamentoControllerRest {

    void findAll(HttpExchange exchange) throws IOException;
}
