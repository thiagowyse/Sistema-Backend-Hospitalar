package com.projeto.repository;

import com.projeto.model.Agendamento;

import java.util.List;

public class AgendamentoRepository implements BaseRepository<Agendamento, Long>{
    @Override
    public void insert(Agendamento entity) {

    }

    @Override
    public Agendamento findById(Long aLong) {
        return null;
    }

    @Override
    public List<Agendamento> findAll() {
        return List.of();
    }

    @Override
    public void update(Agendamento entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
