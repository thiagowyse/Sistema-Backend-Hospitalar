package com.projeto.controller.usuariocontroller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IUsuarioControllerRest {

    void findAll(HttpExchange exchange) throws IOException;



}
