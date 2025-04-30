package com.projeto.controller.agendamentocontroller;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IAgendamentoControllerRest {

    void findAll(HttpExchange exchange) throws IOException;

}
