package com.projeto.service.pacienteservice;

import com.projeto.model.Paciente;

import java.util.List;

public interface IPacienteService {

    Paciente inserirPaciente(Paciente paciente);

    Paciente buscarPacientePorId(Long id);

    List<Paciente> listarTodosPacientes();

    void atualizarPaciente(Paciente paciente);

    void removerPaciente(Long id);
}
