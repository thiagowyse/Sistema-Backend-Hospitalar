package com.projeto.controller.declaracaocontroller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IDeclaracaoControllerRest {

    void findAll(HttpExchange exchange) throws IOException;
}
