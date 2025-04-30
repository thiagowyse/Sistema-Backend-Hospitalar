package com.projeto.controller.logusuariocontroller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface ILogUsuarioControllerRest {

    void findAll(HttpExchange exchange) throws IOException;
}
