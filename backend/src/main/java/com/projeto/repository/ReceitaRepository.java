package com.projeto.repository;

import com.projeto.model.Receita;

import java.util.List;

public class ReceitaRepository implements BaseRepository<Receita, Long> {


    @Override
    public void insert(Receita entity) {

    }

    @Override
    public Receita findById(Long aLong) {
        return null;
    }

    @Override
    public List<Receita> findAll() {
        return List.of();
    }

    @Override
    public void update(Receita entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
