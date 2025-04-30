package com.projeto.controller.medicamentocontroller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IMedicamentoControllerRest {

    void findAll(HttpExchange exchange) throws IOException;
}
