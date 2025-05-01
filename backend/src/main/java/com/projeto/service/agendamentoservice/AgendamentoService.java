package com.projeto.service.agendamentoservice;

import com.projeto.model.Agendamento;
import com.projeto.model.Medico;
import com.projeto.model.Paciente;
import com.projeto.repository.AgendamentoRepository;
import com.projeto.repository.MedicoRepository;
import com.projeto.repository.PacienteRepository;
import com.projeto.service.medicoservice.MedicoService;
import com.projeto.service.pacienteservice.PacienteService;

import java.util.ArrayList;
import java.util.List;

public class AgendamentoService implements IAgendamentoService{

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    @Override
    public Agendamento inserirAgendamento(Agendamento agendamento) {
        return agendamentoRepository.insert(agendamento) ;
    }

    private final PacienteRepository pacienteRepository = new PacienteRepository();
    private final PacienteService pacienteService = new PacienteService(pacienteRepository);

    private final MedicoRepository medicoRepository = new MedicoRepository();
    private final MedicoService medicoService = new MedicoService(medicoRepository);

    @Override
    public Agendamento buscarAgendamentoPorId(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id);

        Paciente paciente = pacienteService.buscarPacientePorId(agendamento.getPaciente().getIdPaciente());
        agendamento.setPaciente(paciente);

        Medico medico = medicoService.buscarMedicoPorId(agendamento.getMedico().getIdMedico());
        agendamento.setMedico(medico);

        return agendamento;
    }

    @Override
    public List<Agendamento> listarTodosAgendamentos() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        List<Agendamento> resposta = new ArrayList<>();

        for(Agendamento agendamento : agendamentos){
            Paciente paciente = pacienteService.buscarPacientePorId(agendamento.getPaciente().getIdPaciente());
            agendamento.setPaciente(paciente);

            Medico medico = medicoService.buscarMedicoPorId(agendamento.getMedico().getIdMedico());
            agendamento.setMedico(medico);

            resposta.add(agendamento);
        }

        return resposta;
    }

    @Override
    public void atualizarAgendamento(Agendamento agendamento) {
        agendamentoRepository.update(agendamento);
    }

    @Override
    public void removerAgendamento(Long id) {
        agendamentoRepository.delete(id);
    }
}
