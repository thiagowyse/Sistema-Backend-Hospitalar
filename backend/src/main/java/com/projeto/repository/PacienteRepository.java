package com.projeto.repository;


import com.projeto.model.Paciente;

import java.util.List;

public class PacienteRepository implements BaseRepository<Paciente, Long>{


    @Override
    public Paciente insert(Paciente entity) {
        return null;
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
