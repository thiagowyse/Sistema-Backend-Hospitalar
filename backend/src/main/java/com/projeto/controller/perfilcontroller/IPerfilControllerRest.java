package com.projeto.controller.perfilcontroller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IPerfilControllerRest {

    void findAll(HttpExchange exchange) throws IOException;
}
