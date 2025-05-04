package com.projeto.controller.prontuariocontroller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Perfil;
import com.projeto.model.Prontuario;
import com.projeto.model.Usuario;
import com.projeto.repository.ProntuarioRepository;
import com.projeto.server.RootController;
import com.projeto.service.prontuarioservice.ProntuarioService;
import com.projeto.util.LocalDateAdapter;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ProntuarioControllerRest extends RootController implements IProntuarioControllerRest {

    ProntuarioRepository prontuarioRepository = new ProntuarioRepository();
    ProntuarioService prontuarioService = new ProntuarioService(prontuarioRepository);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        // Configurar CORS
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // Responder requisições pré-flight (OPTIONS)
        if ("OPTIONS".equalsIgnoreCase(method)) {
            exchange.sendResponseHeaders(204, -1); // Sem conteúdo
            return;
        }

        // Rotas existentes
        if (method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.PRONTUARIO_FIND_ALL.getPath())) {
            findAll(exchange);
        } else if (method.equals(HttpMethod.GET.getMethod()) && path.matches(ApiRotas.PRONTUARIO_FIND_BY_ID.getPath())) {
            findById(exchange);
        } else if (method.equals(HttpMethod.POST.getMethod()) && path.equals(ApiRotas.PRONTUARIO_SAVE.getPath())) {
            save(exchange);
        } else if (method.equals(HttpMethod.PUT.getMethod()) && path.matches(ApiRotas.PRONTUARIO_UPDATE.getPath())) {
            update(exchange);
        } else if (method.equals(HttpMethod.DELETE.getMethod()) && path.matches(ApiRotas.PRONTUARIO_DELETE.getPath())) {
            delete(exchange);
        } else {
            sendResponse(exchange, "Rota não encontrada", 404);
        }
    }


    @Override
    public void findAll(HttpExchange exchange) throws IOException {

        // Usar o GsonBuilder para adicionar o adaptador do LocalDate
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()) // Registra o adaptador
                .create();

        // Listar todos os prontuários
        List<Prontuario> resposta = prontuarioService.listarTodosProntuarios();

        // Converter a lista de prontuários para JSON
        String response = gson.toJson(resposta);

        // Configurar os cabeçalhos de resposta
        exchange.getResponseHeaders().set("Content-Type", "application/json");

        // Enviar a resposta com código HTTP 200
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

        Prontuario prontuario = prontuarioService.buscarProntuarioPorId(id);

        if (prontuario == null) {
            sendResponse(exchange, "Prontuario não encontrado", 404);
            return;
        }

        String response = gson.toJson(prontuario);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        sendResponse(exchange, response, 200);

    }

    @Override
    public void update(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        String body = new String(exchange.getRequestBody().readAllBytes());

        Prontuario prontuario = gson.fromJson(body, Prontuario.class);
        prontuario.setIdProntuario(id);
        prontuarioService.atualizarProntuario(prontuario);

        sendResponse(exchange, "Prontuario atualizado com sucesso", 200);
    }

    @Override
    public void delete(HttpExchange exchange) throws IOException {

        Long id = extractIdFromPath(exchange.getRequestURI().getPath());
        prontuarioService.removerProntuario(id);
        sendResponse(exchange, "Prontuario deletado com sucesso", 200);

    }

    @Override
    public void save(HttpExchange exchange) throws IOException {
        // Cria o Gson com o adaptador customizado para LocalDate
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()) // Registra o adaptador para LocalDate
                .create();

        String body = new String(exchange.getRequestBody().readAllBytes());

        System.out.println("JSON recebido: " + body);

        try {
            // Converte o corpo JSON para um objeto Prontuario
            Prontuario prontuario = gson.fromJson(body, Prontuario.class);

            // Adiciona a data de criação (data atual) ao campo dataCriacao
            prontuario.setDataCriacao(LocalDate.now()); // Atribuindo a data atual

            System.out.println("Objeto Prontuário: " + prontuario);

            // Chama o método para salvar o prontuário
            prontuarioService.inserirProntuario(prontuario);

            // Resposta para sucesso
            sendResponse(exchange, "Prontuário salvo com sucesso", 201);
        } catch (Exception e) {
            e.printStackTrace(); // Mostra o erro completo no console
            sendResponse(exchange, "Erro ao salvar prontuário: " + e.getMessage(), 500);
        }
    }


    private Long extractIdFromPath(String path) {
        String[] parts = path.split("/");
        return Long.parseLong(parts[parts.length - 1]);
    }
}
