package com.projeto.repository;

import com.projeto.model.Enfermeiro;

import java.util.List;

public class EnfermeiroRepository implements BaseRepository<Enfermeiro, Long>{

    @Override
    public Enfermeiro insert(Enfermeiro entity) {

        return entity;
    }

    @Override
    public Enfermeiro findById(Long aLong) {
        return null;
    }

    @Override
    public List<Enfermeiro> findAll() {
        return List.of();
    }

    @Override
    public void update(Enfermeiro entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
