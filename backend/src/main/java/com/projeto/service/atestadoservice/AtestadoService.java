package com.projeto.service.atestadoservice;

import com.projeto.model.Atestado;
import com.projeto.model.Medico;
import com.projeto.model.Paciente;
import com.projeto.repository.AtestadoRepository;
import com.projeto.repository.MedicoRepository;
import com.projeto.repository.PacienteRepository;
import com.projeto.service.medicoservice.MedicoService;
import com.projeto.service.pacienteservice.PacienteService;

import java.util.ArrayList;
import java.util.List;

public class AtestadoService implements IAtestadoService {

    private final AtestadoRepository atestadoRepository;

    private final PacienteRepository pacienteRepository = new PacienteRepository();
    private final PacienteService pacienteService = new PacienteService(pacienteRepository);

    private final MedicoRepository medicoRepository = new MedicoRepository();
    private final MedicoService medicoService = new MedicoService(medicoRepository);

    public AtestadoService(AtestadoRepository atestadoRepository) {
        this.atestadoRepository = atestadoRepository;
    }

    @Override
    public Atestado inserirAtestado(Atestado atestado) {
        return atestadoRepository.insert(atestado);
    }

    @Override
    public Atestado buscarAtestadoPorId(Long id) {
        Atestado atestado = atestadoRepository.findById(id);

        Paciente paciente = pacienteService.buscarPacientePorId(atestado.getPaciente().getIdPaciente());
        atestado.setPaciente(paciente);

        Medico medico = medicoService.buscarMedicoPorId(atestado.getMedico().getIdMedico());
        atestado.setMedico(medico);

        return atestado;
    }

    @Override
    public List<Atestado> listarTodosAtestados() {
        List<Atestado> atestados = atestadoRepository.findAll();
        List<Atestado> resposta = new ArrayList<>();

        for(Atestado atestado : atestados){
            Paciente paciente = pacienteService.buscarPacientePorId(atestado.getPaciente().getIdPaciente());
            atestado.setPaciente(paciente);

            Medico medico = medicoService.buscarMedicoPorId(atestado.getMedico().getIdMedico());
            atestado.setMedico(medico);
            resposta.add(atestado);
        }

        return resposta;
    }

    @Override
    public void atualizarAtestado(Atestado atestado) {
        atestadoRepository.update(atestado);
    }

    @Override
    public void removerAtestado(Long id) {
        atestadoRepository.delete(id);
    }
}
