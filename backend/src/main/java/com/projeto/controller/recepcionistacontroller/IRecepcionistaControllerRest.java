package com.projeto.controller.recepcionistacontroller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IRecepcionistaControllerRest {

    void findAll(HttpExchange exchange) throws IOException;
}
