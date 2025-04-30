package com.projeto.controller.prontuariocontroller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IProntuarioControllerRest {

    void findAll(HttpExchange exchange) throws IOException;
}
