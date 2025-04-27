package com.projeto.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class RootController implements HttpHandler {


    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String response = "Start servidor http";
        sendResponse(exchange, response, 200);
    }

    protected void sendResponse(HttpExchange exchange, String response, int statuscode) throws IOException{

        exchange.sendResponseHeaders(statuscode, response.getBytes().length);

        try(OutputStream os = exchange.getResponseBody()){
            os.write(response.getBytes());
        }
    }
}
