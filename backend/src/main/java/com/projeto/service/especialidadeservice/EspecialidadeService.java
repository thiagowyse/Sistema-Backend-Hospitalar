package com.projeto.service.especialidadeservice;

import com.projeto.model.Especialidade;
import com.projeto.repository.EspecialidadeRepository;

import java.util.List;

public class EspecialidadeService implements IEspecialidadeService{

    private EspecialidadeRepository especialidadeRepository;

    @Override
    public Especialidade inserirEspecialidade(Especialidade especialidade) {
        return especialidadeRepository.insert(especialidade);
    }

    @Override
    public Especialidade buscarEspecialidadePorId(Long id) {
        return especialidadeRepository.findById(id);
    }

    @Override
    public List<Especialidade> listarTodosEspecialidades() {
        return especialidadeRepository.findAll();
    }

    @Override
    public void atualizarEspecialidades(Especialidade especialidade) {
        especialidadeRepository.update(especialidade);
    }

    @Override
    public void removerEspecialidade(Long id) {
        especialidadeRepository.delete(id);
    }
}
