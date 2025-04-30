package com.projeto.controller.medicocontroller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IMedicoControllerRest {

    void findAll(HttpExchange exchange) throws IOException;
}
