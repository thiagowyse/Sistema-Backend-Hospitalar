package com.projeto.controller.pacientecontroller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IPacienteControllerRest {

    void findAll(HttpExchange exchange) throws IOException;
}
