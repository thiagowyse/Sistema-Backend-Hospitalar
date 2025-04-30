package com.projeto.controller.especialidadecontroller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IEspecialidadeControllerRest {

    void findAll(HttpExchange exchange) throws IOException;
}
