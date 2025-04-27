package com.projeto.service.pacienteservice;

import com.projeto.model.Paciente;
import com.projeto.repository.PacienteRepository;

import java.util.List;

public class PacienteService implements IPacienteService{

    private PacienteRepository pacienteRepository;

    @Override
    public Paciente inserirPaciente(Paciente paciente) {
        return pacienteRepository.insert(paciente);
    }

    @Override
    public Paciente buscarPacientePorId(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public List<Paciente> listarTodosPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public void atualizarPaciente(Paciente paciente) {
        pacienteRepository.update(paciente);
    }

    @Override
    public void removerPaciente(Long id) {
        pacienteRepository.delete(id);
    }
}
