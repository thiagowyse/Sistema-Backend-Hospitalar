package com.projeto.repository;

import com.projeto.model.Medicamento;

import java.util.List;

public class MedicamentoRepository implements BaseRepository<Medicamento, Long> {

    @Override
    public Medicamento insert(Medicamento entity) {

        return entity;
    }

    @Override
    public Medicamento findById(Long aLong) {
        return null;
    }

    @Override
    public List<Medicamento> findAll() {
        return List.of();
    }

    @Override
    public void update(Medicamento entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
