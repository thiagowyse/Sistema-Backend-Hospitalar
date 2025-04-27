package com.projeto.repository;

import com.projeto.model.LogUsuario;

import java.util.List;

public class LogUsuarioRepository implements BaseRepository<LogUsuario,Long> {
    @Override
    public LogUsuario insert(LogUsuario entity) {

        return entity;
    }

    @Override
    public LogUsuario findById(Long aLong) {
        return null;
    }

    @Override
    public List<LogUsuario> findAll() {
        return List.of();
    }

    @Override
    public void update(LogUsuario entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
