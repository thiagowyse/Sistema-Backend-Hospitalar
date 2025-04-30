package com.projeto.controller.atestadocontroller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IAtestadoControllerRest {

    void findAll(HttpExchange exchange) throws IOException;
}
