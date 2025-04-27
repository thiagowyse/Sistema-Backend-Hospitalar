package com.projeto.repository;

import com.projeto.model.Medico;

import java.util.List;

public class MedicoEspecialidade implements BaseRepository<Medico, Long> {
    @Override
    public Medico insert(Medico entity) {

        return entity;
    }

    @Override
    public Medico findById(Long aLong) {
        return null;
    }

    @Override
    public List<Medico> findAll() {
        return List.of();
    }

    @Override
    public void update(Medico entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
