package com.projeto.service;

import com.projeto.model.Paciente;
import com.projeto.repository.PacienteRepository;

import java.util.List;

public class PacienteService {

    private PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente buscarPacientePorId(int id) {
        return pacienteRepository.buscarPacientePorId(id);
    }
}
