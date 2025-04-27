package com.projeto.app;


import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args)  throws IOException {

        final int port = 8080;

        HttpServer server = HttpServer.create(new InetSocketAddress(port),0);

        server.setExecutor(null);
        server.start();
        System.out.println("Servidor iniciado na porta " + port);



    }}

