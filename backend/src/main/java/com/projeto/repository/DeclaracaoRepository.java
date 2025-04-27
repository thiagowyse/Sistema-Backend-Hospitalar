package com.projeto.repository;

import com.projeto.model.Declaracao;

import java.util.List;

public class DeclaracaoRepository implements BaseRepository<Declaracao, Long> {
    @Override
    public Declaracao insert(Declaracao entity) {

        return entity;
    }

    @Override
    public Declaracao findById(Long aLong) {
        return null;
    }

    @Override
    public List<Declaracao> findAll() {
        return List.of();
    }

    @Override
    public void update(Declaracao entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
