package com.projeto.controller.enfermeirocontroller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IEnfermeiroControllerRest {

    void findAll(HttpExchange exchange) throws IOException;

}
