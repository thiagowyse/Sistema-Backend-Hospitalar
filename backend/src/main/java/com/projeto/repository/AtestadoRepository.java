package com.projeto.repository;

import com.projeto.model.Atestado;

import java.util.List;

public class AtestadoRepository implements BaseRepository<Atestado, Long>{
    @Override
    public void insert(Atestado entity) {

    }

    @Override
    public Atestado findById(Long aLong) {
        return null;
    }

    @Override
    public List<Atestado> findAll() {
        return List.of();
    }

    @Override
    public void update(Atestado entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
