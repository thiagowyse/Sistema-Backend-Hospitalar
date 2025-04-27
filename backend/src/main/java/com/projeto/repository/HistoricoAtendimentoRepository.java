package com.projeto.repository;

import com.projeto.model.HistoricoAtendimento;

import java.util.List;

public class HistoricoAtendimentoRepository implements BaseRepository<HistoricoAtendimento, Long>{

    @Override
    public HistoricoAtendimento insert(HistoricoAtendimento entity) {

        return entity;
    }

    @Override
    public HistoricoAtendimento findById(Long aLong) {
        return null;
    }

    @Override
    public List<HistoricoAtendimento> findAll() {
        return List.of();
    }

    @Override
    public void update(HistoricoAtendimento entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
