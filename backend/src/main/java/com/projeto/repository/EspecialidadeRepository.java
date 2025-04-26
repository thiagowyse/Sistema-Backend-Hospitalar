package com.projeto.repository;

import com.projeto.model.Especialidade;

import java.util.List;

public class EspecialidadeRepository implements BaseRepository<Especialidade, Long>{
    @Override
    public void insert(Especialidade entity) {

    }

    @Override
    public Especialidade findById(Long aLong) {
        return null;
    }

    @Override
    public List<Especialidade> findAll() {
        return List.of();
    }

    @Override
    public void update(Especialidade entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
