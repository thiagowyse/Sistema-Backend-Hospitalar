package com.projeto.controller.especialidadecontroller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IEspecialidadeControllerRest {

    void findAll(HttpExchange exchange) throws IOException;

    void findById(HttpExchange exchange) throws IOException;

    void update(HttpExchange exchange) throws IOException;

    void delete(HttpExchange exchange) throws IOException;

    void save(HttpExchange exchange) throws IOException;
}
