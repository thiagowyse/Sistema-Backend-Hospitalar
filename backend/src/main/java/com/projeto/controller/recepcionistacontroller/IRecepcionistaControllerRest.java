package com.projeto.controller.recepcionistacontroller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IRecepcionistaControllerRest {

    void findAll(HttpExchange exchange) throws IOException;

    void findById(HttpExchange exchange) throws IOException;

    void update(HttpExchange exchange) throws IOException;

    void delete(HttpExchange exchange) throws IOException;

    void save(HttpExchange exchange) throws IOException;
}
