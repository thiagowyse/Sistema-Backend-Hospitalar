package com.projeto.controller.usuariocontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Perfil;
import com.projeto.model.Prontuario;
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


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        // Habilitar CORS
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");

        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.USUARIO_FIND_ALL.getPath())){
            findAll(exchange);
        } else if (method.equals(HttpMethod.GET.getMethod()) && path.matches(ApiRotas.USUARIO_FIND_BY_ID.getPath())) {
            findById(exchange);
        } else if (method.equals(HttpMethod.POST.getMethod()) && path.equals(ApiRotas.USUARIO_SAVE.getPath())) {
            save(exchange);
        } else if (method.equals(HttpMethod.PUT.getMethod()) && path.matches(ApiRotas.USUARIO_UPDATE.getPath())) {
            update(exchange);
        } else if (method.equals(HttpMethod.DELETE.getMethod()) && path.matches(ApiRotas.USUARIO_DELETE.getPath())) {
            delete(exchange);
        } else {
            sendResponse(exchange, "Rota não encontrada", 404);
        }

    }

    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        List<Usuario> resposta = usuarioService.listarTodosUsuarios();

        String response = gson.toJson(resposta);

        exchange.getResponseHeaders().set("Content-Type", "application/json");

        sendResponse(exchange, response, 200);
    }

    @Override
    public void findById(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        String query = exchange.getRequestURI().getQuery();
        Long id = null;

        if (query != null && query.contains("id=")) {
            String[] params = query.split("&");
            for (String param : params) {
                if (param.startsWith("id=")) {
                    id = Long.parseLong(param.split("=")[1]);
                    break;
                }
            }
        }

        if (id == null) {
            sendResponse(exchange, "Parâmetro 'id' é obrigatório", 400);
            return;
        }

        Usuario usuario = usuarioService.buscarUsuarioPorId(id);


        if (usuario == null) {
            sendResponse(exchange, "Usuário não encontrado", 404);
            return;
        }

        String response = gson.toJson(usuario);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        sendResponse(exchange, response, 200);

    }

    @Override
    public void update(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        String body = new String(exchange.getRequestBody().readAllBytes());

        Usuario usuario = gson.fromJson(body, Usuario.class);
        usuario.setIdUsuario(id);
        usuarioService.atualizarUsuario(usuario);


        sendResponse(exchange, "Usuário atualizado com sucesso", 200);

    }

    @Override
    public void delete(HttpExchange exchange) throws IOException {

        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        usuarioService.removerUsuario(id);
        sendResponse(exchange, "Usuário deletado com sucesso", 200);
    }

    @Override
    public void save(HttpExchange exchange) throws IOException {

    }

    private Long extractIdFromPath(String path) {
        String[] parts = path.split("/");
        return Long.parseLong(parts[parts.length - 1]);
    }
}
