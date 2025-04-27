package com.projeto.service.medicoservice;

import com.projeto.model.Medico;

import java.util.List;

public interface IMedicoService {

    Medico inserirMedico(Medico medico);

    Medico buscarMedicoPorId(Long id);

    List<Medico> listarTodosMedicos();

    void atualizarMedico(Medico medico);

    void removerMedico(Long id);
}
