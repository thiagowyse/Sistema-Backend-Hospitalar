package com.projeto.controller.receitacontroller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IReceitaControllerRest {

    void findAll(HttpExchange exchange) throws IOException;
}
