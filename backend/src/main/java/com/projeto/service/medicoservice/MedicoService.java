package com.projeto.service.medicoservice;

import com.projeto.model.Medico;
import com.projeto.repository.MedicoRepository;

import java.util.List;

public class MedicoService implements IMedicoService{

    private MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    public Medico inserirMedico(Medico medico) {
        return medicoRepository.insert(medico);
    }

    @Override
    public Medico buscarMedicoPorId(Long id) {
        return medicoRepository.findById(id);
    }

    @Override
    public List<Medico> listarTodosMedicos() {
        return medicoRepository.findAll();
    }

    @Override
    public void atualizarMedico(Medico medico) {
        medicoRepository.update(medico);
    }

    @Override
    public void removerMedico(Long id) {
        medicoRepository.delete(id);
    }
}
