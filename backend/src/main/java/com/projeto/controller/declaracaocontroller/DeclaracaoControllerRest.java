package com.projeto.controller.declaracaocontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Atestado;
import com.projeto.model.Declaracao;
import com.projeto.model.Medico;
import com.projeto.model.Paciente;
import com.projeto.repository.DeclaracaoRepository;
import com.projeto.repository.MedicoRepository;
import com.projeto.repository.PacienteRepository;
import com.projeto.server.RootController;
import com.projeto.service.declaracaoservice.DeclaracaoService;
import com.projeto.service.medicoservice.MedicoService;
import com.projeto.service.pacienteservice.PacienteService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeclaracaoControllerRest extends RootController implements IDeclaracaoControllerRest {

    DeclaracaoRepository declaracaoRepository = new DeclaracaoRepository();
    DeclaracaoService declaracaoService = new DeclaracaoService(declaracaoRepository);

    PacienteRepository pacienteRepository = new PacienteRepository();
    PacienteService pacienteService = new PacienteService(pacienteRepository);

    MedicoRepository medicoRepository = new MedicoRepository();
    MedicoService medicoService = new MedicoService(medicoRepository);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.DECLARACAO_FIND_ALL.getPath())){
            findAll(exchange);
        }
    }


    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        List<Declaracao> listDeclaracao = declaracaoService.listarTodosDeclaracoes();
        List<Declaracao> resposta = new ArrayList<>();

        for(Declaracao declaracao : listDeclaracao){
            Paciente paciente = pacienteService.buscarPacientePorId(declaracao.getIdPaciente());
            declaracao.setPaciente(paciente);
            Medico medico = medicoService.buscarMedicoPorId(declaracao.getIdMedico());
            declaracao.setMedico(medico);
            resposta.add(declaracao);
        }

        String response = gson.toJson(resposta);

        exchange.getResponseHeaders().set("Content-Type", "application/json");

        sendResponse(exchange,response, 200);
    }
}
