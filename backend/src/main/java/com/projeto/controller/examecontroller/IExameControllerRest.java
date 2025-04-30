package com.projeto.controller.examecontroller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IExameControllerRest {

    void findAll(HttpExchange exchange) throws IOException;
}
