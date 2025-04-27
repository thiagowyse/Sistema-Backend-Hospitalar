package com.projeto.service.medicamentoservice;

import com.projeto.model.Medicamento;
import com.projeto.repository.MedicamentoRepository;

import java.util.List;

public class MedicamentoService implements IMedicamentoService{

    private MedicamentoRepository medicamentoRepository;

    @Override
    public Medicamento inserirMedicamento(Medicamento medicamento) {
        return medicamentoRepository.insert(medicamento);
    }

    @Override
    public Medicamento buscarMedicamentoPorId(Long id) {
        return medicamentoRepository.findById(id);
    }

    @Override
    public List<Medicamento> listarTodosMedicamentos() {
        return medicamentoRepository.findAll();
    }

    @Override
    public void atualizarMedicamento(Medicamento medicamento) {
        medicamentoRepository.update(medicamento);
    }

    @Override
    public void removerMedicamento(Long id) {
        medicamentoRepository.delete(id);
    }
}
