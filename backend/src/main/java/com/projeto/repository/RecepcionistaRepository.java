package com.projeto.repository;

import com.projeto.model.Recepcionista;

import java.util.List;

public class RecepcionistaRepository implements BaseRepository<Recepcionista, Long> {
    @Override
    public Recepcionista insert(Recepcionista entity) {

        return entity;
    }

    @Override
    public Recepcionista findById(Long aLong) {
        return null;
    }

    @Override
    public List<Recepcionista> findAll() {
        return List.of();
    }

    @Override
    public void update(Recepcionista entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
