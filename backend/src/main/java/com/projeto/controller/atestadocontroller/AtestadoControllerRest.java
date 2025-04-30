package com.projeto.controller.atestadocontroller;

import com.google.gson.Gson;
import com.projeto.enums.ApiRotas;
import com.projeto.enums.HttpMethod;
import com.projeto.model.Agendamento;
import com.projeto.model.Atestado;
import com.projeto.model.Medico;
import com.projeto.model.Paciente;
import com.projeto.repository.AtestadoRepository;
import com.projeto.repository.MedicoRepository;
import com.projeto.repository.PacienteRepository;
import com.projeto.server.RootController;
import com.projeto.service.atestadoservice.AtestadoService;
import com.projeto.service.medicoservice.MedicoService;
import com.projeto.service.pacienteservice.PacienteService;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AtestadoControllerRest extends RootController implements IAtestadoControllerRest {

    AtestadoRepository atestadoRepository = new AtestadoRepository();
    AtestadoService atestadoService = new AtestadoService(atestadoRepository);

    PacienteRepository pacienteRepository = new PacienteRepository();
    PacienteService pacienteService = new PacienteService(pacienteRepository);

    MedicoRepository medicoRepository = new MedicoRepository();
    MedicoService medicoService = new MedicoService(medicoRepository);

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if(method.equals(HttpMethod.GET.getMethod()) && path.equals(ApiRotas.ATESTADO_FIND_ALL.getPath())){
            findAll(exchange);
        }
    }

    @Override
    public void findAll(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        List<Atestado> listAtestado = atestadoService.listarTodosAtestados();
        List<Atestado> resposta = new ArrayList<>();

        for(Atestado atestado : listAtestado){
            Paciente paciente = pacienteService.buscarPacientePorId(atestado.getIdPaciente());
            atestado.setPaciente(paciente);
            Medico medico = medicoService.buscarMedicoPorId(atestado.getIdMedico());
            atestado.setMedico(medico);
            resposta.add(atestado);
        }

        String response = gson.toJson(resposta);

        exchange.getResponseHeaders().set("Content-Type", "application/json");

        sendResponse(exchange,response, 200);

    }
}
