package com.projeto.service.historicoatendimentoservice;

import com.projeto.model.HistoricoAtendimento;
import com.projeto.model.Medico;
import com.projeto.model.Paciente;
import com.projeto.repository.HistoricoAtendimentoRepository;
import com.projeto.repository.MedicoRepository;
import com.projeto.repository.PacienteRepository;
import com.projeto.service.medicoservice.MedicoService;
import com.projeto.service.pacienteservice.PacienteService;

import java.util.ArrayList;
import java.util.List;

public class HistoricoAtendimentoService implements IHistoricoAtendimentoService{

    private final HistoricoAtendimentoRepository historicoAtendimentoRepository;

    public HistoricoAtendimentoService(HistoricoAtendimentoRepository historicoAtendimentoRepository) {
        this.historicoAtendimentoRepository = historicoAtendimentoRepository;
    }

    private final PacienteRepository pacienteRepository = new PacienteRepository();
    private final PacienteService pacienteService = new PacienteService(pacienteRepository);

    private final MedicoRepository medicoRepository = new MedicoRepository();
    private final MedicoService medicoService = new MedicoService(medicoRepository);

    @Override
    public HistoricoAtendimento inserirHistoricoAtendimento(HistoricoAtendimento historicoAtendimento) {
        return historicoAtendimentoRepository.insert(historicoAtendimento);
    }

    @Override
    public HistoricoAtendimento buscarHistoricoAtendimentoPorId(Long id) {
        HistoricoAtendimento historicoAtendimento = historicoAtendimentoRepository.findById(id);

        Paciente paciente = pacienteService.buscarPacientePorId(historicoAtendimento.getPaciente().getIdPaciente());
        historicoAtendimento.setPaciente(paciente);

        Medico medico = medicoService.buscarMedicoPorId(historicoAtendimento.getMedico().getIdMedico());
        historicoAtendimento.setMedico(medico);
        return historicoAtendimento;
    }

    @Override
    public List<HistoricoAtendimento> listarTodosHistoricoAtendimentos() {
        List<HistoricoAtendimento> historicoAtendimentos = historicoAtendimentoRepository.findAll();
        List<HistoricoAtendimento> resposta = new ArrayList<>();

        for(HistoricoAtendimento historicoAtendimento : historicoAtendimentos){
            Paciente paciente = pacienteService.buscarPacientePorId(historicoAtendimento.getPaciente().getIdPaciente());
            historicoAtendimento.setPaciente(paciente);

            Medico medico = medicoService.buscarMedicoPorId(historicoAtendimento.getMedico().getIdMedico());
            historicoAtendimento.setMedico(medico);
            resposta.add(historicoAtendimento);

        }
        return resposta;
    }

    @Override
    public void atualizarHistoricoAtendimento(HistoricoAtendimento historicoAtendimento) {
        historicoAtendimentoRepository.update(historicoAtendimento);
    }

    @Override
    public void removerHistoricoAtendimento(Long id) {
        historicoAtendimentoRepository.delete(id);
    }
}
