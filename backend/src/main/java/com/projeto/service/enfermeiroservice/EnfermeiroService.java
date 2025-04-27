package com.projeto.service.enfermeiroservice;

import com.projeto.model.Enfermeiro;
import com.projeto.repository.EnfermeiroRepository;

import java.util.List;

public class EnfermeiroService implements IEnfermeiroService{

    private EnfermeiroRepository enfermeiroRepository;

    @Override
    public Enfermeiro inserirEnfermeiro(Enfermeiro enfermeiro) {
        return enfermeiroRepository.insert(enfermeiro);
    }

    @Override
    public Enfermeiro buscarEnfermeiroPorId(Long id) {
        return enfermeiroRepository.findById(id);
    }

    @Override
    public List<Enfermeiro> listarTodosEnfermeiros() {
        return enfermeiroRepository.findAll();
    }

    @Override
    public void atualizarEnfermeiro(Enfermeiro enfermeiro) {
        enfermeiroRepository.update(enfermeiro);
    }

    @Override
    public void removerEnfermeiro(Long id) {
        enfermeiroRepository.delete(id);
    }
}
