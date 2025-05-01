package com.projeto.controller.usuariocontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Perfil;
import com.projeto.model.Usuario;
import com.projeto.repository.PerfilRepository;
import com.projeto.repository.UsuarioRepository;
import com.projeto.server.RootController;
import com.projeto.service.perfilservice.PerfilService;
import com.projeto.service.usuarioservice.UsuarioService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioControllerRest extends RootController implements IUsuarioControllerRest {

    UsuarioRepository usuarioRepository = new UsuarioRepository();
    UsuarioService usuarioService = new UsuarioService(usuarioRepository);

    PerfilRepository perfilRepository = new PerfilRepository();
    PerfilService perfilService = new PerfilService(perfilRepository);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        // Habilitar CORS
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");

        if (method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.USUARIO_FIND_ALL.getPath())) {
            findAll(exchange);
        }


    }

    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        List<Usuario> listUsuario = usuarioService.listarTodosUsuarios();
        List<Usuario> resposta = new ArrayList<>();

        for (Usuario usuario : listUsuario) {
            Perfil perfil = perfilService.buscarPerfilPorId(usuario.getIdPerfil());
            usuario.setPerfil(perfil);
            resposta.add(usuario);
        }

        String response = gson.toJson(resposta);

        // Definindo o tipo de conteúdo como JSON
        exchange.getResponseHeaders().set("Content-Type", "application/json");

        // Enviando a resposta
        sendResponse(exchange, response, 200);
    }
}
