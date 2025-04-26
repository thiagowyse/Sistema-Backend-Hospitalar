package com.projeto.repository;

import com.projeto.model.Exame;

import java.util.List;

public class ExameRepository implements BaseRepository<Exame,Long>{
    @Override
    public void insert(Exame entity) {

    }

    @Override
    public Exame findById(Long aLong) {
        return null;
    }

    @Override
    public List<Exame> findAll() {
        return List.of();
    }

    @Override
    public void update(Exame entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
