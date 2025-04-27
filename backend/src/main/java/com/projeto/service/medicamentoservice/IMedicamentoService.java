package com.projeto.service.medicamentoservice;

import com.projeto.model.Medicamento;

import java.util.List;

public interface IMedicamentoService {

    Medicamento inserirMedicamento(Medicamento medicamento);

    Medicamento buscarMedicamentoPorId(Long id);

    List<Medicamento> listarTodosMedicamentos();

    void atualizarMedicamento(Medicamento medicamentos);

    void removerMedicamento(Long id);
}
