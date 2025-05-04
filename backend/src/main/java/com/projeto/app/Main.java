package com.projeto.app;


import com.projeto.controller.agendamentocontroller.AgendamentoControllerRest;
import com.projeto.controller.atestadocontroller.AtestadoControllerRest;
import com.projeto.controller.declaracaocontroller.DeclaracaoControllerRest;
import com.projeto.controller.enfermeirocontroller.EnfermeiroControllerRest;
import com.projeto.controller.especialidadecontroller.EspecialidadeControllerRest;
import com.projeto.controller.examecontroller.ExameControllerRest;
import com.projeto.controller.historicoatendimentocontroller.HistoricoAtendimentoControllerRest;
import com.projeto.controller.logusuariocontroller.LogUsuarioControllerRest;
import com.projeto.controller.medicamentocontroller.MedicamentoControllerRest;
import com.projeto.controller.medicocontroller.MedicoControllerRest;
import com.projeto.controller.pacientecontroller.PacienteControllerRest;
import com.projeto.controller.perfilcontroller.PerfilControllerRest;
import com.projeto.controller.prontuariocontroller.ProntuarioControllerRest;
import com.projeto.controller.prontuarioexamecontroller.ProntuarioExameControllerRest;
import com.projeto.controller.prontuariomedicamentocontroller.ProntuarioMedicamentoControllerRest;
import com.projeto.controller.receitacontroller.ReceitaControllerRest;
import com.projeto.controller.receitamedicamentocontroller.ReceitaMedicamentoControllerRest;
import com.projeto.controller.recepcionistacontroller.RecepcionistaControllerRest;
import com.projeto.controller.usuariocontroller.UsuarioControllerRest;
import com.projeto.enums.ApiRotas;
import com.projeto.server.RootController;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args)  throws IOException {

        final int port = 9090;

        HttpServer server = HttpServer.create(new InetSocketAddress(port),0);

        //Rotas da Api
        server.createContext("/", new RootController());
        server.createContext(ApiRotas.AGENDAMENTO.getPath(), new AgendamentoControllerRest());
        server.createContext(ApiRotas.ATESTADO.getPath(), new AtestadoControllerRest());
        server.createContext(ApiRotas.DECLARACAO.getPath(), new DeclaracaoControllerRest());
        server.createContext(ApiRotas.ENFERMEIRO.getPath(), new EnfermeiroControllerRest());
        server.createContext(ApiRotas.ESPECIALIDADE.getPath(), new EspecialidadeControllerRest());
        server.createContext(ApiRotas.EXAME.getPath(), new ExameControllerRest());
        server.createContext(ApiRotas.HISTORICO_ATENDIMENTO.getPath(), new HistoricoAtendimentoControllerRest());
        server.createContext(ApiRotas.LOG_USUARIO.getPath(), new LogUsuarioControllerRest());
        server.createContext(ApiRotas.MEDICAMENTO.getPath(), new MedicamentoControllerRest());
        server.createContext(ApiRotas.MEDICO.getPath(), new MedicoControllerRest());
        server.createContext(ApiRotas.PACIENTE.getPath(), new PacienteControllerRest());
        server.createContext(ApiRotas.PERFIL.getPath(), new PerfilControllerRest());
        server.createContext(ApiRotas.PRONTUARIO.getPath(), new ProntuarioControllerRest());
        server.createContext(ApiRotas.PRONTUARIO_EXAME.getPath(), new ProntuarioExameControllerRest());
        server.createContext(ApiRotas.PRONTUARIO_MEDICAMENTO.getPath(), new ProntuarioMedicamentoControllerRest());
        server.createContext(ApiRotas.RECEITA.getPath(), new ReceitaControllerRest());
        server.createContext(ApiRotas.RECEPCIONISTA.getPath(), new RecepcionistaControllerRest());
        server.createContext(ApiRotas.USUARIO.getPath(), new UsuarioControllerRest());
        server.createContext(ApiRotas.RECEITA_MEDICAMENTO.getPath(), new ReceitaMedicamentoControllerRest());

        server.setExecutor(null);
        server.start();
        System.out.println("Servidor iniciado na porta " + port);



    }}

