package com.projeto.service.recepcionistaservice;

import com.projeto.model.Recepcionista;
import com.projeto.repository.RecepcionistaRepository;

import java.util.List;

public class RecepcionistaService implements IRecepcionistaService{

    private final RecepcionistaRepository recepcionistaRepository;

    public RecepcionistaService(RecepcionistaRepository recepcionistaRepository) {
        this.recepcionistaRepository = recepcionistaRepository;
    }

    @Override
    public Recepcionista inserirRecepcionista(Recepcionista recepcionista) {
        return recepcionistaRepository.insert(recepcionista);
    }

    @Override
    public Recepcionista buscarRecepcionistaPorId(Long id) {
        return recepcionistaRepository.findById(id);
    }

    @Override
    public List<Recepcionista> listarTodosRecepcionistas() {
        return recepcionistaRepository.findAll();
    }

    @Override
    public void atualizarRecepcionista(Recepcionista recepcionista) {
        recepcionistaRepository.update(recepcionista);
    }

    @Override
    public void removerRecepcionista(Long id) {
        recepcionistaRepository.delete(id);
    }
}
