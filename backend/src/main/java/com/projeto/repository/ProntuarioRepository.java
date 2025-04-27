package com.projeto.repository;

import com.projeto.model.Prontuario;

import java.util.List;

public class ProntuarioRepository implements BaseRepository<Prontuario,Long> {
    @Override
    public Prontuario insert(Prontuario entity) {
        return null;
    }

    @Override
    public Prontuario findById(Long aLong) {
        return null;
    }

    @Override
    public List<Prontuario> findAll() {
        return List.of();
    }

    @Override
    public void update(Prontuario entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
