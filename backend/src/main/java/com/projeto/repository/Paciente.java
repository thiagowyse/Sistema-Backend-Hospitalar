package com.projeto.repository;

import java.util.List;

public class Paciente implements BaseRepository<Paciente, Long>{
    @Override
    public void insert(Paciente entity) {

    }

    @Override
    public Paciente findById(Long aLong) {
        return null;
    }

    @Override
    public List<Paciente> findAll() {
        return List.of();
    }

    @Override
    public void update(Paciente entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
