package com.projeto.service.prontuarioservice;

import com.projeto.model.Medico;
import com.projeto.model.Paciente;
import com.projeto.model.Prontuario;
import com.projeto.repository.MedicoRepository;
import com.projeto.repository.PacienteRepository;
import com.projeto.repository.ProntuarioRepository;
import com.projeto.service.medicoservice.MedicoService;
import com.projeto.service.pacienteservice.PacienteService;

import java.util.ArrayList;
import java.util.List;

public class ProntuarioService implements IProntuarioService{

    private final ProntuarioRepository prontuarioRepository;

    public ProntuarioService(ProntuarioRepository prontuarioRepository) {
        this.prontuarioRepository = prontuarioRepository;
    }

    private final PacienteRepository pacienteRepository = new PacienteRepository();
    private final PacienteService pacienteService = new PacienteService(pacienteRepository);

    private final MedicoRepository medicoRepository = new MedicoRepository();
    private final MedicoService medicoService = new MedicoService(medicoRepository);


    @Override
    public Prontuario inserirProntuario(Prontuario prontuario) {
        return prontuarioRepository.insert(prontuario);
    }

    @Override
    public Prontuario buscarProntuarioPorId(Long id) {
        Prontuario prontuario = prontuarioRepository.findById(id);

        Paciente paciente = pacienteService.buscarPacientePorId(prontuario.getPaciente().getIdPaciente());
        prontuario.setPaciente(paciente);

        Medico medico = medicoService.buscarMedicoPorId(prontuario.getMedico().getIdMedico());
        prontuario.setMedico(medico);

        return prontuario;
    }

    @Override
    public List<Prontuario> listarTodosProntuarios() {
        List<Prontuario> prontuarios = prontuarioRepository.findAll();
        List<Prontuario> resposta = new ArrayList<>();
        for(Prontuario prontuario : prontuarios){

            Paciente paciente = pacienteService.buscarPacientePorId(prontuario.getPaciente().getIdPaciente());
            prontuario.setPaciente(paciente);

            Medico medico = medicoService.buscarMedicoPorId(prontuario.getMedico().getIdMedico());
            prontuario.setMedico(medico);

            resposta.add(prontuario);
        }

        return resposta;
    }

    @Override
    public void atualizarProntuario(Prontuario prontuario) {
        prontuarioRepository.update(prontuario);
    }

    @Override
    public void removerProntuario(Long id) {
        prontuarioRepository.delete(id);
    }
}
