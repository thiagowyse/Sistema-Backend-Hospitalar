package com.projeto.app;


import com.projeto.controller.UsuarioControllerRest;
import com.projeto.enums.ApiRotas;
import com.projeto.server.RootController;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args)  throws IOException {

        final int port = 8080;

        HttpServer server = HttpServer.create(new InetSocketAddress(port),0);

        //Rotas da Api

        server.createContext("/", new RootController());
        server.createContext(ApiRotas.USUARIO.getPath(), new UsuarioControllerRest());
        server.setExecutor(null);
        server.start();
        System.out.println("Servidor iniciado na porta " + port);



    }}

